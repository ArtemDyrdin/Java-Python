package db;

public class Const {
    public static class Budget {
        public static final String BUDGET = "Бюджет";
        public static final String BUDGETAMT = "Сумма";
    }

    public static class Week {
        public static final String WEEK = "ЦельНеделя";
        public static final String WEEKAMT = "Сумма";
        public static final String DATE = "Дата";
    }

    public static class Month {
        public static final String MONTH = "ЦельМесяц";
        public static final String MONTHAMT = "Сумма";
        public static final String DATE = "Дата";

    }

    public static class Persons {
        public static final String PERSONTBL = "[Члены семьи]";
        public static final String NAME = "Имя";
    }
    public static class Origins {
        public static final String INCOMETBL = "Источники";
        public static final String INCOME = "Название";
    }

    public static class Expences {
        public static final String EXPENCETBL = "[Классы расходов]";
        public static final String EXPENCE = "Название";
    }

    public static class ExpenceL {
        public static final String EXPENCETBL = "Расходы";
        public static final String AMOUNT = "Сумма";
        public static final String EXPENCECLASSID = "idКлассаРасхода";
        public static final String PERSONID = "idЧленаСемьи";
        public static final String DATE = "Дата";
    }

    public static class Income {
        public static final String INCOMETBL = "Доходы";
        public static final String AMOUNT = "Сумма";
        public static final String ORIGINID = "idИсточника";
        public static final String PERSONID = "idЧленаСемьи";
        public static final String DATE = "Дата";
    }
}
