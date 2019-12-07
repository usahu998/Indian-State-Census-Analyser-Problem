package com.bridgelabz.statecensusanalyserproblem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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


    private static String SAMPLE_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/CSVStatesCensusJsonFile.json";
    private static String POPULATION_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/Population .json";
    private static String POPULATION_DENSITY_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/PopulationDensity.json";
    private static String LARGEST_STATE_AREA_JSON_FILE_PATH = "/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/LargestStateArea.json";
    private String filePath;

    public StateCensusAnalyser(String filePath) {
        this.filePath = filePath;
    }

    CSVStatesCensus csvStatesCensus = new CSVStatesCensus();
    CSVStates csvStates = new CSVStates();

    public <E> int readStateData(Class<E> eClass, String sortBy) throws CensusCsvException {
        int count = 0;
        List<E> statesCensusList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(eClass)
                    .build();
            Iterator<E> stateIterator = csvToBean.iterator();
            while (stateIterator.hasNext()) {
                E csv = stateIterator.next();
                statesCensusList.add((E) csv);
                count++;
                if (csv instanceof CSVStatesCensus) {
                    if (((CSVStatesCensus) csv).getState() == null || ((CSVStatesCensus) csv).getAreaInSqKm() == null || ((CSVStatesCensus) csv).getDensityPerSqKm() == null || ((CSVStatesCensus) csv).getPopulation() == null) {
                        throw new CensusCsvException("Exception due to Header or mismatch data", CensusCsvException.ExceptionType.NO_SUCH_HEADER);
                    }
                }
                if (csv instanceof CSVStates) {
                    if (((CSVStates) csv).getSrNo() == null || ((CSVStates) csv).getStateCode() == null || ((CSVStates) csv).getStateName() == null || ((CSVStates) csv).getTIN() == null) {
                        throw new CensusCsvException("Exception due to Header or mismatch data", CensusCsvException.ExceptionType.NO_SUCH_HEADER);
                    }
                }
            }
            sortingList((List<CSVStatesCensus>) statesCensusList, sortBy);
        } catch (NoSuchFileException e) {
            if (filePath.contains(".csv")) {
                throw new CensusCsvException("Please enter proper file name", CensusCsvException.ExceptionType.NO_SUCH_FILE);
            }
        } catch (RuntimeException e) {
            throw new CensusCsvException("Exception due to incorrect delimiter position", CensusCsvException.ExceptionType.NO_SUCH_FIELD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void sortingList(List<CSVStatesCensus> statesCensusList, String sortType) throws IOException {
        if (sortType.equals("stateAlphabeticalOrder")) {
            Comparator<CSVStatesCensus> statesCensusComparator = Comparator.comparing(CSVStatesCensus::getState);
            writeToJson(statesCensusList, SAMPLE_JSON_FILE_PATH);
        }
        if (sortType.equals("mostPopulatedState")) {
            Comparator<CSVStatesCensus> statesCensusComparator = Comparator.comparing(CSVStatesCensus::getPopulation);
            statesCensusList.sort(statesCensusComparator);
            writeToJson(statesCensusList, POPULATION_JSON_FILE_PATH);
        }
        if (sortType.equals("populationDensityState")) {
            Comparator<CSVStatesCensus> statesCensusComparator = Comparator.comparing(CSVStatesCensus::getDensityPerSqKm);
            statesCensusList.sort(statesCensusComparator);
            writeToJson(statesCensusList, POPULATION_DENSITY_JSON_FILE_PATH);
        }
        if (sortType.equals("largestStateArea")) {
            Comparator<CSVStatesCensus> statesCensusComparator = Comparator.comparing(CSVStatesCensus::getAreaInSqKm);
            statesCensusList.sort(statesCensusComparator);
            writeToJson(statesCensusList, LARGEST_STATE_AREA_JSON_FILE_PATH);
        }
    }

    public static void writeToJson(List<CSVStatesCensus> statesCensusList, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(statesCensusList);
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }
}