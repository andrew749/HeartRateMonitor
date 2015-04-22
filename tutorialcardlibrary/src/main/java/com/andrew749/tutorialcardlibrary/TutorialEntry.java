package com.andrew749.tutorialcardlibrary;

import java.io.Serializable;

/**
 * Created by andrew on 12/20/14.
 */
public class TutorialEntry implements Serializable {
    int image;
    String description;

    public TutorialEntry(int image, String string) {
        description = string;
        this.image = image;

    }
}
