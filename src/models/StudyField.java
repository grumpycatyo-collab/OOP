package models;

public enum StudyField {
    MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE;

    public static StudyField findStudyField(String field) {
        for (StudyField s : StudyField.values()) {
            if (s.name().equalsIgnoreCase(field)) {
                return s;
            }
        }
        return null;
    }
}

