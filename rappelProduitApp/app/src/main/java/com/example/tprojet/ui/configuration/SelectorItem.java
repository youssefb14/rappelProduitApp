package com.example.tprojet.ui.configuration;

public class SelectorItem {
    private String title;
    private String[] options;
    private String selectedOption;

    public SelectorItem(String title, String[] options, String selectedOption) {
        this.title = title;
        this.options = options;
        this.selectedOption = selectedOption;
    }

    public String getTitle() {
        return title;
    }

    public String[] getOptions() {
        return options;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
