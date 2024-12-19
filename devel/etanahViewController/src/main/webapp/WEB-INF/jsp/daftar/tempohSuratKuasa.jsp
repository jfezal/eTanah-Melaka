<%-- 
    Document   : jenis_suratkuasa
    Created on : Dec 15, 2009, 5:27:12 PM
    Author     : mohd.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>




<s:form name="form1" beanclass="etanah.view.daftar.SuratKuasaWakilActionBean">

    <s:messages />
    <s:errors />
                            <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Suratkuasa Wakil
            </legend>
            <p>
                <label >Tarikh Daftar :</label>
                <s:text name="tkhDaftar" class="datepicker"/>
            </p>
            <p>
                <label>Tempoh :</label>
                <s:text name="wakilPihak.tempohTahun" style="width:2%"/>&nbsp;Tahun&nbsp;&nbsp;
                <s:text name="wakilPihak.tempohBulan" style="width:2%"/>&nbsp;Bulan&nbsp;&nbsp;
                <s:text name="wakilPihak.tempohHari" style="width:2%"/>&nbsp;Hari
            </p>
            <p>
                <label>Nombor Mahkamah :</label>
                <s:text name="wakilPihak.noRujukan" />
            </p>


            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanTempoh" value="Simpan"/>

                            <br/>
                            <br/>
                        </div>
                    </td>
                </tr>
            </table>

        </fieldset>


    </div>
    <br>
</s:form>
