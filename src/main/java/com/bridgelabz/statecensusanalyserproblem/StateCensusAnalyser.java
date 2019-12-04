package com.bridgelabz.statecensusanalyserproblem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.bridgelabz.statecensusanalyserproblem.CSVStatesCensus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {

    private String CSV_FILE_PATH;
    private static String SAMPLE_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/CSVStatesCensusJsonFile.json";
    private static String POPULATION_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/Population .json";
    private static String POPULATION_DENSITY_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/PopulationDensity.json";
    private static String LARGEST_STATE_AREA_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/LargestStateArea.json";

    public StateCensusAnalyser(String CSV_FILE_PATH) {
        this.CSV_FILE_PATH = CSV_FILE_PATH;
    }

    CSVStatesCensus csvStatesCensus = new CSVStatesCensus();
    CSVStates csvStates = new CSVStates();

    public <E> int readStateData(Class<E> eClass) throws CensusCsvException {
        int count = 0;
        List<CSVStatesCensus> statesCensusList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(eClass)
                    .build();
            Iterator stateIterator = csvToBean.iterator();
            while (stateIterator.hasNext()) {
                CSVStatesCensus csv = (CSVStatesCensus) stateIterator.next();
                statesCensusList.add(csv);
                count++;
                if (csv instanceof CSVStatesCensus) {
                    if (((CSVStatesCensus) csv).getState() == null || ((CSVStatesCensus) csv).getAreaInSqKm() == null || ((CSVStatesCensus) csv).getDensityPerSqKm() == null || ((CSVStatesCensus) csv).getPopulation() == null) {
                        throw new CensusCsvException("Exception due to Header or mismatch data", CensusCsvException.ExceptionType.NO_SUCH_HEADER);
                    }
                }
            }
            stateAlphabeticalOrder((List<CSVStatesCensus>) statesCensusList);
            mostPopulatedState((List<CSVStatesCensus>) statesCensusList);
            populationDensityState((List<CSVStatesCensus>) statesCensusList);
            largestStateArea((List<CSVStatesCensus>) statesCensusList);
        } catch (NoSuchFileException e) {
            if (CSV_FILE_PATH.contains(".csv")) {
                throw new CensusCsvException("Please enter proper file name", CensusCsvException.ExceptionType.NO_SUCH_FILE);
            }
        } catch (RuntimeException e) {
            throw new CensusCsvException("Exception due to incorrect delimiter position", CensusCsvException.ExceptionType.NO_SUCH_FIELD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void stateAlphabeticalOrder(List<CSVStatesCensus> statesCensusList) throws IOException {
        Comparator<CSVStatesCensus> c = (s1, s2) -> s1.getState().compareTo(s2.getState());
        statesCensusList.sort(c);
        System.out.println(statesCensusList);
        writeToJson(statesCensusList, SAMPLE_JSON_FILE_PATH);
    }

    public void mostPopulatedState(List<CSVStatesCensus> statesCensusList) throws IOException {
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getPopulation().trim()) - Integer.parseInt(s1.getPopulation().trim());
        statesCensusList.sort(c);
        System.out.println(statesCensusList);
        writeToJson(statesCensusList, POPULATION_JSON_FILE_PATH);
    }

    public void populationDensityState(List<CSVStatesCensus> statesCensusList) throws IOException {
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getDensityPerSqKm().trim()) - Integer.parseInt(s1.getDensityPerSqKm().trim());
        statesCensusList.sort(c);
        System.out.println(statesCensusList);
        writeToJson(statesCensusList, POPULATION_DENSITY_JSON_FILE_PATH);
    }

    public void largestStateArea(List<CSVStatesCensus> statesCensusList) throws IOException {
        Comparator<CSVStatesCensus> c = (s1, s2) -> Integer.parseInt(s2.getAreaInSqKm().trim()) - Integer.parseInt(s1.getAreaInSqKm().trim());
        statesCensusList.sort(c);
        System.out.println(statesCensusList);
        writeToJson(statesCensusList, LARGEST_STATE_AREA_JSON_FILE_PATH);
    }

    public static void writeToJson(List<CSVStatesCensus> statesCensusList, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(statesCensusList);
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }
}