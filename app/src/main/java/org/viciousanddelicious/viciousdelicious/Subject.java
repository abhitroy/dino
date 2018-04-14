package org.viciousanddelicious.viciousdelicious;

import java.io.Serializable;

/**
 * Created by rahul on 12/4/18.
 */

public class Subject implements Serializable {

        String OD_ML;
        String absenthours;
        String atthours;
        String average;
        String maxhours;
        String subjectcode;

        String subjectname;
        String total;

        Subject(String subjectcode,String subjectname,String maxhours,String atthours,String absenthours,String average,String OD_ML,String total)
        {
            this.OD_ML=OD_ML;
            this.absenthours=absenthours;
            this.atthours=atthours;
            this.average=average;
            this.maxhours=maxhours;
            this.subjectcode=subjectcode;
            this.subjectname=subjectname;
            this.total=total;
        }

        void setAbsenthours(String paramString)
        {
            this.absenthours = paramString;
        }


        void setAtthours(String paramString)
        {
            this.atthours = paramString;
        }

        void setAverage(String paramString)
        {
            this.average = paramString;
        }

        void setMaxhours(String paramString)
        {
            this.maxhours = paramString;
        }

        void setOD_ML(String paramString)
        {
            this.OD_ML = paramString;
        }

        void setSubjectcode(String paramString)
        {
            this.subjectcode = paramString;
        }



        void setSubjectname(String paramString)
        {
            this.subjectname = paramString;
        }

        void setTotal(String paramString)
        {
            this.total = paramString;
        }
    }

