package com.university.librarymanagementsystem.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import org.springframework.web.client.RestTemplate;

@Service
public class MetadataService {

    private final String SRU_BASE_URL = "http://lx2.loc.gov:210/LCDB?";

    public List<Map<String, String>> retrieveMetadata(String query) {
        String url = SRU_BASE_URL + "version=1.1&operation=searchRetrieve&query=" + query
                + "&startRecord=1&maximumRecords=10&recordSchema=mods";

        RestTemplate restTemplate = new RestTemplate();
        String xmlResponse = restTemplate.getForObject(url, String.class);
        return parseMetadata(xmlResponse);
    }

    private List<Map<String, String>> parseMetadata(String xml) {
        List<Map<String, String>> books = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // Ensures namespaces are considered
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));

            NodeList records = doc.getElementsByTagNameNS("http://www.loc.gov/zing/srw/", "recordData");
            for (int i = 0; i < records.getLength(); i++) {
                Element record = (Element) records.item(i);
                Map<String, String> book = new HashMap<>();

                // Extract required metadata fields
                book.put("title", getTagValue(record, "titleInfo", "title"));
                book.put("author", getTagValue(record, "name", "namePart"));

                book.put("publisher", getTagValue(record, "originInfo", "publisher"));
                book.put("datePublished", getTagValue(record, "originInfo", "dateIssued"));
                book.put("description", getTagValue(record, "abstract", "abstract"));
                book.put("ISBN", getTagValue(record, "identifier", "isbn"));
                book.put("ISSN", getTagValue(record, "identifier", "issn"));
                book.put("LCClassification", getTagValue(record, "classification", "classification"));
                book.put("summary", getTagValue(record, "abstract", "abstract"));
                book.put("subjects", getTagValues(record, "subject"));
                book.put("callNumber", getTagValue(record, "classification", "callNumber"));
                book.put("notes", getTagValues(record, "note"));
                book.put("resourceType", getTagValue(record, "typeOfResource", "typeOfResource"));
                book.put("conferenceName", getTagValue(record, "name", "conferenceName"));
                book.put("corporateAuthor", getTagValue(record, "name", "corporateAuthor"));
                book.put("corporateName", getTagValue(record, "name", "corporateName"));
                book.put("personalAuthor", getTagValue(record, "name", "personalAuthor"));
                book.put("personalName", getTagValue(record, "name", "personalName"));
                book.put("seriesTitle", getTagValue(record, "relatedItem", "title"));
                book.put("pages", getTagValue(record, "physicalDescription", "extent"));
                book.put("aboutTheAuthor", getTagValue(record, "note", "aboutTheAuthor"));

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    private String getTagValue(Element element, String parentTag, String tagName) {
        NodeList parentNodeList = element.getElementsByTagNameNS("http://www.loc.gov/mods/v3", parentTag);
        if (parentNodeList.getLength() > 0) {
            Element parentElement = (Element) parentNodeList.item(0);
            NodeList nodeList = parentElement.getElementsByTagNameNS("http://www.loc.gov/mods/v3", tagName);
            if (nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            }
        }
        return "N/A";
    }

    private String getTagValues(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagNameNS("http://www.loc.gov/mods/v3", tagName);
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            values.append(nodeList.item(i).getTextContent()).append("; ");
        }
        return values.length() > 0 ? values.toString() : "N/A";
    }
}
