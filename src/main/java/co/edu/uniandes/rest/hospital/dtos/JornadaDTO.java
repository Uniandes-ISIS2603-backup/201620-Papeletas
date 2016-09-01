/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;


/**
 *
 * @author ac.cabezas716
 */
public class JornadaDTO {

    private HorarioDTO.DiaSemana diaSemana;

    private int horaIni;

    private int horaFin;

    public JornadaDTO(HorarioDTO.DiaSemana pDia, int pHoraI, int pHoraF) {
        diaSemana = pDia;
        horaIni = pHoraI;
        horaFin = pHoraF;
    }

    /**
     * @return the diaSemana
     */
    public HorarioDTO.DiaSemana getDiaSemana() {
        return diaSemana;
    }

    /**
     * @param diaSemana the diaSemana to set
     */
    public void setDiaSemana(HorarioDTO.DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * @return the horaIni
     */
    public int getHoraIni() {
        return horaIni;
    }

    /**
     * @param horaIni the horaIni to set
     */
    public void setHoraIni(int horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * @return the horaFin
     */
    public int getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

}
