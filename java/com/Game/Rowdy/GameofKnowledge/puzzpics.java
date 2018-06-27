package com.Game.Rowdy.GameofKnowledge;

public class puzzpics
{
    int tiger[]={R.drawable.three1,R.drawable.three2,R.drawable.three3,R.drawable.three4,
            R.drawable.three5,R.drawable.three6,R.drawable.three7,R.drawable.three8,R.drawable.three9};
    int fish[]={R.drawable.four1,R.drawable.four2,R.drawable.four3,R.drawable.four4,R.drawable.four5,R.drawable.four6,R.drawable.four7,R.drawable.four8,R.drawable.four9,
            R.drawable.four10,R.drawable.four11,R.drawable.four12,R.drawable.four13,R.drawable.four14,R.drawable.four15,R.drawable.four16};
    int elephant[]={R.drawable.five1,R.drawable.five2,R.drawable.five3,R.drawable.five4,R.drawable.five5,R.drawable.five6,R.drawable.five7,R.drawable.five8,
            R.drawable.five9,R.drawable.five10,R.drawable.five11,R.drawable.five12,R.drawable.five13,R.drawable.five14,R.drawable.five15,R.drawable.five16,R.drawable.five17,
            R.drawable.five18,R.drawable.five19,R.drawable.five20,R.drawable.five21,R.drawable.five22,R.drawable.five23,R.drawable.five24,R.drawable.five25};
    int frog[]={R.drawable.six1,R.drawable.six2,R.drawable.six3,R.drawable.six4,R.drawable.six5,R.drawable.six6,R.drawable.six7,R.drawable.six8,R.drawable.six9,
            R.drawable.six10,R.drawable.six11,R.drawable.six12,R.drawable.six13,R.drawable.six14,R.drawable.six15,R.drawable.six16,R.drawable.six17,R.drawable.six18,R.drawable.six19,
            R.drawable.six20,R.drawable.six21,R.drawable.six22,R.drawable.six23,R.drawable.six24,R.drawable.six25,R.drawable.six26,R.drawable.six27,R.drawable.six28,R.drawable.six29,
            R.drawable.six30,R.drawable.six31,R.drawable.six32,R.drawable.six33,R.drawable.six34,R.drawable.six35,R.drawable.six36};
    public int[] choose(int n)
    {   int a[]=new int[20];
        if(n==0)
            a=tiger;
        else if(n==1)
            a=fish;
        else if(n==2)
            a=elephant;
        else if(n==3)
            a=frog;
        return a;
    }
}
