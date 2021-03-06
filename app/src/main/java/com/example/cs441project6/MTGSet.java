package com.example.cs441project6;

import androidx.annotation.NonNull;

public class MTGSet {
    private boolean standardLegal;
    private boolean pioneerLegal;
    private boolean modernLegal;
    private boolean legacyLegal;
    private boolean historicLegal;
    private boolean beenChosen;
    private String name;

    public MTGSet(boolean standard, boolean pioneer, boolean modern, boolean legacy, boolean historic, String name)
    {
        this.standardLegal = standard;
        this.pioneerLegal = pioneer;
        this.modernLegal = modern;
        this.legacyLegal = legacy;
        this.historicLegal = historic;

        this.name = name;
    }
    public boolean isStandardLegal()
    {
        return this.standardLegal;
    }
    public boolean isPioneerLegal()
    {
        return this.pioneerLegal;
    }
    public boolean isModernLegal()
    {
        return this.modernLegal;
    }
    public boolean isLegacyLegal()
    {
        return this.legacyLegal;
    }
    public boolean isHistoricLegal()
    {
        return this.historicLegal;
    }
    public boolean isBeenChosen()
    {
        return this.beenChosen;
    }
    public String choose()
    {
        this.beenChosen = true;
        return this.name;
    }

    @NonNull
    @Override
    public String toString() {
        String s = this.name;
        if(this.standardLegal)
        {
            s.concat(": T, ");
        }
        else
        {
            s.concat(": F, ");
        }

        if(this.pioneerLegal)
        {
            s.concat(" T, ");
        }
        else
        {
            s.concat(" F, ");
        }

        if(this.modernLegal)
        {
            s.concat(" T, ");
        }
        else
        {
            s.concat(" F, ");
        }

        if(this.legacyLegal)
        {
            s.concat(" T, ");
        }
        else
        {
            s.concat(" F, ");
        }

        if(this.historicLegal)
        {
            s.concat(" T, ");
        }
        else
        {
            s.concat(" F, ");
        }
        return s;
    }
}
