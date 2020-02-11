/*
 * Copyright 2000-2019 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.datetimepicker.demo;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link DateTimePicker} demo.
 *
 * @author Vaadin Ltd
 */
@Route("vaadin-date-time-picker")
public class DateTimePickerView extends DemoView {

    @Override
    public void initView() {
        basicDemo(); // Basic usage
        valueChangeEvent();
        finnishDateTimePicker(); // Localizing
        themeVariantsTextAlign(); // Theme variants
        themeVariantsSmallSize();
        styling(); // Styling
    }

    private void basicDemo() {
        Div div = new Div();
        // begin-source-example
        // source-example-heading: Basic usage
        DateTimePicker dateTimePicker = new DateTimePicker();

        DateTimePicker valueDateTimePicker = new DateTimePicker();
        LocalDateTime now = LocalDateTime.now();
        valueDateTimePicker.setValue(now);
        // end-source-example

        dateTimePicker.getStyle().set("margin-right", "5px");
        div.add(dateTimePicker, valueDateTimePicker);
        addCard("Basic usage", div);
    }

    private void valueChangeEvent() {
        // begin-source-example
        // source-example-heading: Value change event
        DateTimePicker dateTimePicker = new DateTimePicker();

        Div value = new Div();
        value.setText("Select a value");
        dateTimePicker.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                value.setText("No date time selected");
            } else {
                value.setText("Selected date time: " + event.getValue());
            }
        });
        // end-source-example

        Paragraph note = new Paragraph(
                "Note: The date time picker has a non-empty value only when"
                        + " both a date and a time have been selected.");
        VerticalLayout verticalLayout = new VerticalLayout(note, dateTimePicker,
                value);
        verticalLayout.setPadding(false);
        addCard("Value change event", verticalLayout);
    }

    private void finnishDateTimePicker() {
        Div message = new Div();
        /*
         * TODO: This needs to be extended to be similar to DatePicker
         * Localizing demo once setI18n() has been implemented
         */
        // begin-source-example
        // source-example-heading: Localizing
        DateTimePicker dateTimePicker = new DateTimePicker();
        Locale localeFI = new Locale("fi");
        dateTimePicker.setLocale(localeFI);

        dateTimePicker.addValueChangeListener(event -> {
            LocalDateTime selectedDateTime = event.getValue();
            if (selectedDateTime != null) {
                String weekdayName = selectedDateTime.getDayOfWeek()
                        .getDisplayName(TextStyle.FULL, localeFI);
                String monthName = selectedDateTime.getMonth()
                        .getDisplayName(TextStyle.FULL, localeFI);

                message.setText("Day of week: " + weekdayName + ", Month: "
                        + monthName);
            } else {
                message.setText("No date is selected");
            }
        });
        // end-source-example

        addCard("Localization", "Localizing", dateTimePicker, message);
    }

    private void themeVariantsTextAlign() {
        VerticalLayout verticalLayout = new VerticalLayout();
        // begin-source-example
        // source-example-heading: Text align
        DateTimePicker leftDateTimePicker = new DateTimePicker();
        leftDateTimePicker.setValue(LocalDateTime.now());
        leftDateTimePicker.addThemeName("align-left");

        DateTimePicker centerDateTimePicker = new DateTimePicker();
        centerDateTimePicker.setValue(LocalDateTime.now());
        centerDateTimePicker.addThemeName("align-center");

        DateTimePicker rightDateTimePicker = new DateTimePicker();
        rightDateTimePicker.setValue(LocalDateTime.now());
        rightDateTimePicker.addThemeName("align-right");
        // end-source-example

        verticalLayout.add(leftDateTimePicker, centerDateTimePicker,
                rightDateTimePicker);
        verticalLayout.setPadding(false);
        addCard("Theme Variants", "Text align", verticalLayout);
    }

    private void themeVariantsSmallSize() {
        // begin-source-example
        // source-example-heading: Small text field
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.addThemeName("small");
        // end-source-example

        addCard("Theme Variants", "Small text field", dateTimePicker);
    }

    private void styling() {
        Div firstDiv = new Div();
        firstDiv.setText(
                "To read about styling you can read the related tutorial in");
        Anchor firstAnchor = new Anchor(
                "https://vaadin.com/docs/flow/theme/using-component-themes.html",
                "Using Component Themes");

        Div secondDiv = new Div();
        secondDiv.setText("To know about styling in HTML you can read the ");
        Anchor secondAnchor = new Anchor("https://vaadin.com/components/"
                + "vaadin-date-time-picker/html-examples/date-time-picker-styling-demos",
                "HTML Styling Demos");

        HorizontalLayout firstHorizontalLayout = new HorizontalLayout(firstDiv,
                firstAnchor);
        HorizontalLayout secondHorizontalLayout = new HorizontalLayout(
                secondDiv, secondAnchor);
        // begin-source-example
        // source-example-heading: Styling references

        // end-source-example

        addCard("Styling", "Styling references", firstHorizontalLayout,
                secondHorizontalLayout);
    }
}
