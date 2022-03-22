//Reference Links:
//https://stackoverflow.com/questions/40031037/deep-cloning-an-object-of-a-class-in-java
//Used copy constructor for deep copy via the .clone method as deep copy is difficult without serializable

public class Term implements Comparable<Term>, Cloneable{

    //Fields
    private int mCoefficient;
    private int mExponent;
    private Term mNext;

    //Empty Constructor
    public Term() {
        mCoefficient = 1;
        mExponent = 1;
    }

    //Full Constructor
    public Term(int coefficient, int exponent)
    {
        mCoefficient = coefficient;
        mExponent = exponent;
    }

    //String Constructor
    public Term(String term) {
        int[] temp = parseTerm(term);
        mCoefficient = temp[0];
        mExponent = temp[1];
    }

    //Copy Constructor
    public Term(Term term) {
        this.mCoefficient = term.mCoefficient;
        this.mExponent = term.mExponent;
    }

    public Object clone() {
        Term t = new Term(this);
        return t;
    }

    //Setters
    public void setCoefficient(int coefficient) {
        this.mCoefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.mExponent = exponent;
    }

    public void setAll(int coefficient, int exponent) {
        this.mCoefficient = coefficient;
        this.mExponent = exponent;
    }

    //Getters
    public int getCoefficient() {
        return mCoefficient;
    }

    public int getExponent() {
        return mExponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return mCoefficient == term.mCoefficient && mExponent == term.mExponent;
    }

    @Override
    public int compareTo(Term o) {
        return Integer.compare(mExponent, o.mExponent);
    }

    @Override
    public String toString() {
        String output, coefficient, exponent;
        coefficient = Integer.toString(mCoefficient);
        exponent = Integer.toString(mExponent);

        //Coefficient
        if (mCoefficient == 0) //Cancels the whole term
            return "";
        else if (mCoefficient == 1 || mCoefficient == -1)
            coefficient = ((mCoefficient == 1) ? "+" : "-");
        else if (mCoefficient > 0)
            coefficient = "+" + coefficient;

        //Exponent
        if (mExponent == 0)
            exponent = "";
        else if (mExponent == 1)
            exponent = "x";
        else
            exponent = "x^" + exponent;

        output = coefficient + exponent;

        return output;
    }

    //Helper Methods
    //Parse incoming term string and return:
    //1) coefficient
    //2) exponent

    private int[] parseTerm(String userInput) {

        int[] parsedTerm = new int[2];
        int tempIndex;

        if (userInput.equals(""))
            return parsedTerm;

        if (userInput.charAt(0) == '+')
            userInput = userInput.substring(1);

        if (userInput.contains("x")) {
            tempIndex = userInput.indexOf("x");

            //coefficient [0]
            if (tempIndex == 0)
                parsedTerm[0] = 1;
            else if (userInput.contains("-x"))
                parsedTerm[0] = -1;
            else
                parsedTerm[0] = Integer.parseInt(userInput.substring(0, tempIndex));

            if (userInput.contains("^")) {
                tempIndex = userInput.indexOf("^");

                //exponent [1]
                parsedTerm[1] = Integer.parseInt(userInput.substring(tempIndex + 1));
            } else {
                parsedTerm[1] = 1;
            }
        } else {
            //coefficient [0]
            parsedTerm[0] = Integer.parseInt(userInput);
            //exponent [1]
            //parsedTerm[1] = 0; (zero by default)
        }

        return parsedTerm;
    }
}