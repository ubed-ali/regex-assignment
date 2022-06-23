import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.FileWriter;

public class ExtractData {
    public static void main(String args[]) throws IOException {
        String location = "/home/savera/Desktop/regexAssignment/sample-example.html";
        String line, data = "";
        BufferedReader lineReader = new BufferedReader(new FileReader(location));

        while ((line = lineReader.readLine()) != null) {
            data += line + "\n";
        }

        Pattern appRefPattern = Pattern.compile("([A-Z]{2}\\d{8}-\\d{5})", Pattern.DOTALL);
        Matcher matcher1 = appRefPattern.matcher(data);
        ArrayList<String> appRefData = new ArrayList<String>();
        int count = 0;
        while(matcher1.find()) {
            count++;
            appRefData.add(matcher1.group());
        }

        Pattern patientNamePattern  = Pattern.compile("(\">[A-Za-z]{3,}\\s+[A-Za-z]{3,})", Pattern.DOTALL);
        Matcher matcher2 = patientNamePattern.matcher(data);
        ArrayList<String> patNameData = new ArrayList<String>();
        while(matcher2.find()) {
            count++;
            patNameData.add(matcher2.group());
        }

        Pattern addressPattern = Pattern.compile("(\\w{1,}\\s*\\w{1,}\\s\\w{1,},\\s)?(\\w{1,}\\s)?(\\w{1,},\\s)?(\\w(1,)\\s)?(\\w{1,}\\s)?(\\w{1,}\\s)?(\\w{1,}\\s\\w{1,},\\s)?\\w{1,},\\s*(\\s*|\\w{1,})\\s\\w{1,},\\s\\w{1,},.\\d{5}", Pattern.DOTALL);
        Matcher matcher3 = addressPattern.matcher(data);
        ArrayList<String> addressData = new ArrayList<String>();
        while(matcher3.find()) {
            count++;
            addressData.add(matcher3.group());
        }

        Pattern phonePattern = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}-\\d{4}", Pattern.DOTALL);
        Matcher matcher4 = phonePattern.matcher(data);
        ArrayList<String> phoneData = new ArrayList<String>();
        while(matcher4.find()) {
            count++;
            phoneData.add(matcher4.group());
        }


        Pattern agentNamePattern = Pattern.compile("(\">[A-Za-z]{3,}\\s+[A-Za-z]{3,}|(N\\/A))", Pattern.DOTALL);
        Matcher matcher5 = agentNamePattern.matcher(data);
        ArrayList<String> agentData = new ArrayList<String>();
        while(matcher5.find()) {
            count++;
            agentData.add(matcher5.group());
        }

        Pattern apptStatusPattern = Pattern.compile("(COMPLETED|CONFIRMED|AVAILABLE)", Pattern.DOTALL);
        Matcher matcher6 = apptStatusPattern.matcher(data);
        ArrayList<String> apptData = new ArrayList<String>();
        while(matcher6.find()) {
            count++;
            apptData.add(matcher6.group());
        }

        Pattern trackingPattern = Pattern.compile("\\s(\\d{5,}|(n\\/a))", Pattern.DOTALL);
        Matcher matcher7 = trackingPattern.matcher(data);
        ArrayList<String> trackingData = new ArrayList<String>();
        while(matcher7.find()) {
            count++;
            trackingData.add(matcher7.group());
        }

        Pattern apptDateTimePattern = Pattern.compile("(\\d{2}\\s[a-zA-Z]{3}\\s\\d{4}\\s\\d{2}:\\d{2}\\s[A-Z]{2})", Pattern.DOTALL);
        Matcher matcher8 = apptDateTimePattern.matcher(data);
        ArrayList<String> apptDateTimeData = new ArrayList<String>();
        while(matcher8.find()) {
            count++;
            apptDateTimeData.add(matcher8.group());
        }

        int patientNo;
        int totalPatients = appRefData.size();
        String patientData = "";


        for (patientNo = 0; patientNo < totalPatients; patientNo++) {
            patientData += appRefData.get(patientNo) + "  " + patNameData.get(patientNo+1)+ "  " + addressData.get(patientNo).replaceAll(" \n" +
                    "     ", " ") + "  " + phoneData.get(patientNo)+ "  " + agentData.get(patientNo+1) + "  " + apptData.get(patientNo)+ "  " + trackingData.get(patientNo) + "  " +apptDateTimeData.get(patientNo) + "\n";
        }

        FileWriter fw=new FileWriter("/home/savera/Desktop/patientData.txt");
        fw.write(patientData);
        fw.close();
    }
}