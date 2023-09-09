package components;

public enum Prop {
    DURATION("4 месяца"),
    STARTFROM("31 августа"),
    TITLE("QA Engineer. Basic"),
    DESCRIPTION("Получите навыки тестирования веб-приложений на основе реального проекта наших партнёров."),
    FORMAT("Онлайн");

    private String name;

    Prop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
