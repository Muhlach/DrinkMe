package com.drinkme.sdm.myapplication.utils;

import com.drinkme.sdm.myapplication.EstadisticasFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ssant on 26/12/2017.
 */

public class FechaUtils {

    public static int getToday() {
        Calendar fecha = Calendar.getInstance();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int ano = fecha.get(Calendar.YEAR);

        int hoy = formateaAInt(dia, mes, ano);

        return hoy;
    }


    private static int formateaAInt(int dia, int mes, int ano) {
        return ano*10000 + mes*100 + dia;
    }

    public static int[] getRango(int fechaId) {
        int[] result = new int[2];

        switch (fechaId) {
            case EstadisticasFragment.SEMANA:
                desglosaFechaSemana(result);
                break;
            case EstadisticasFragment.MES:
                desglosaFechaMes(result);
                break;
            case EstadisticasFragment.ANIO:
                desglosaFechaAnio(result);
                break;
        }

        return result;
    }

    private static void desglosaFechaAnio(int[] result) {
        Calendar fecha = Calendar.getInstance();
        int ano = fecha.get(Calendar.YEAR);
        result[0] = formateaAInt(1,1, ano);
        result[1] = formateaAInt(31,12, ano);
    }

    private static void desglosaFechaMes(int[] result) {
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;
        int ano = fecha.get(Calendar.YEAR);
        result[0] = formateaAInt(1, mes, ano);
        if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12)
            result[1] = formateaAInt(31, mes, ano);
        else if(mes==2 && ano%4==0)
            result[1] = formateaAInt(29, mes, ano);
        else if(mes==2)
            result[1] = formateaAInt(28, mes, ano);
        else
            result[1] = formateaAInt(30, mes, ano);
    }

    private static void desglosaFechaSemana(int[] result) {
        Calendar fecha = Calendar.getInstance();
        int ano = fecha.get(Calendar.YEAR);

        int[] diaYmes = getFirstDayOfTheWeekInTheMonthAndHisMonth(fecha.getTime());
        result[1] = formateaAInt(diaYmes[0], diaYmes[1] , ano);

        int[] diaYmes2 = getLastDayOfTheWeekInTheMonthAndHisMonth(fecha.getTime());
        result[2] = formateaAInt(diaYmes2[0], diaYmes2[1] , ano);
    }

    public static int getHora() {
        Calendar fecha = Calendar.getInstance();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int horamin = hora*100+minuto;
        return horamin;
    }

    /**
     *
     * @param date
     * @return result[1] -> day ///////// result[2] -> month
     */
    private  static int[]  getLastDayOfTheWeekInTheMonthAndHisMonth(Date date) {
        boolean inside = false;
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        while (calendar2.get(Calendar.DAY_OF_WEEK) - 1 < 6 && calendar2.get(Calendar.DAY_OF_WEEK) - 1 != 0) {
            calendar2.add(Calendar.DATE, 1);
            inside = true;
        }

        if (calendar2.get(Calendar.DAY_OF_WEEK) - 1 == 6)
            inside = true;

        int result[] = new int[2];
        if (inside) {
            result[0] = (calendar2.get(Calendar.DAY_OF_MONTH) + 1);
        } else {
            result[0] = calendar2.get(Calendar.DAY_OF_MONTH);
        }

        result[1] = (calendar2.get(Calendar.MONTH) + 1);

        return result;
    }

    /**
     *
     * @param date
     * @return result[1] -> day ///////// result[2] -> month
     */
    private static int[] getFirstDayOfTheWeekInTheMonthAndHisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (calendar.get(Calendar.DAY_OF_WEEK) - 1 > 1 || calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0) {
            calendar.add(Calendar.DATE, -1);
        }

        int result[] = new int[2];

        result[0] = calendar.get(Calendar.DAY_OF_MONTH);
        result[1] = (calendar.get(Calendar.MONTH) + 1);

        return result;
    }
}