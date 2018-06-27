package com.Game.Rowdy.GameofKnowledge;

public class Mcq
{
    public String question(int i)
    {
        String ques[]={"Which is a primary color?",
                "Which of the following is used in pencils?",
                "106 × 106 – 94 × 94 = ?",
                "Conversion of 33.33% in decimal is ?",
                "Which organ purify our blood?",
                "The intersecting lines drawn on maps and globes are",
                "Which country won most silver medals in Rio Olympics 2016?",
                "How many moons does the planet Uranus have ?",
                "Which of the following is a perfect square?",
                "Who invented the light bulb facts?",
                "Which animal can create the loudest sound among any living creature?",
                "Which bird which lays only one egg in two years?"};

        return ques[i];
    }
    public String op1(int i)
    {
        String opa[]={"GREEN","Graphite","2032","33/100","Kidney","Latitudes","United Kingdom","31","4620","Albert Einstein","Whale shark","Albatross"};
        return opa[i];
    }
    public String op2(int i)
    {
        String opb[]={"YELLOW","Charcoal","2400","0.033","Heart","Longitudes","USA","27","4636","Thomas Edison","Humpback Whales","Ostrich"};
        return opb[i];
    }
    public String op3(int i)
    {
        String opc[]={"ORANGE","Phosphorous","2440","0.33","Liver","Geographic grids","China","29","4624","Sir Isaac Newton","Howler monkey","Emu"};
        return opc[i];
    }
    public int answer(int i)
    {
        int a[]={2,1,2,3,1,3,2,2,3,2,2,1};
        return a[i];
    }
}
