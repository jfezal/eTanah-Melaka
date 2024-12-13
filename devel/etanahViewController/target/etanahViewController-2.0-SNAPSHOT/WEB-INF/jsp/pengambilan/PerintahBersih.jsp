<%-- 
    Document   : PerintahBersih
    Created on : 26-Oct-2010, 15:17:55
    Author     : nordiyana
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.stripes.pengambilan.notifikasiActionBean">
   <s:messages/>
<s:errors/>
<div>
    <fieldset class="aras1">
        <legend align="center">
                <b>Deraf Perintah</b>
            </legend>

        <c:if test="${edit}">
                 <%--<c:if test="${actionBean.permohonan.kodUrusan eq '831A'}">
                    <tr>
                 <label for="status permohonan">Jenis Permohonan :</label>
                 <s:radio name="" value="Segera"/>&nbsp;Segera
                 <s:radio name="" value="Tidak Segera"/>&nbsp;Tidak Segera
                 </tr>
                </c:if>--%>

        
      <table align="center">
        <tr>
            <td align="left" width="30%"><label for="Maklumat Pengambilan">Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label></td>
            <td><s:text name="Nama" size="30" id="norujukan" /></
        </tr>
        <tr>
            <td><label for="Trh_Surat_ Memohon">Saman Pemula Bil :</label></td>
            <td><s:text name="bilpemula" size="30" id="maksudpengambilan"/></td>
        </tr>

        <tr>
            <td><label for="Maksud_Pengambilan">Tarikh Saman Pemula/Saman Dalam Kamar :</label></td>
           <td><s:text name="tarikhSaman" id="datepicker" class="datepicker" /></td>
        </tr>
             <tr>
            <td><label for="Maksud_Pengambilan">Masa Saman Pemula/Saman Dalam Kamar :</label></td>
           <td><s:text name="masa" size="30" id="maksudpengambilan"/></td>
        </tr>
         <tr>
            <td><label for="Maksud_Pengambilan">Lokasi Saman Pemula/Saman Dalam Kamar :</label></td>
           <td><s:text name="lokasi" size="30" id="maksudpengambilan"/></td>
        </tr>
        <tr>
            <td><label for="Trh_Surat_ Memohon">Tarikh Ikrar :</label></td>
            <td><s:text name="tarikhRujukan" id="datepicker" class="datepicker" /></td>
        </tr>
             <%--<tr>
                <label>  Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bpm"  >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                </s:select>
            </tr>
            --%>
            <br>
            <tr align="center">
                <td> <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  /></td>
               <%-- onclick="save1(this.name, this.form);"--%>
            </tr>
        </table>
      </c:if>
      
      <c:if test="${!edit}">
         <%-- <tr>
            <label for="Maklumat Pengambilan">Jenis Permohonan :</label>Segera&nbsp;
          </tr>--%>
          <tr>
            <label for="Maklumat Pengambilan">Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label>TIADA DATA<%--${actionBean.permohonanRujukanLuar.noRujukan}--%>&nbsp;
          </tr>
        <tr>
            <label for="Trh_Surat_ Memohon">Saman Pemula Bil :</label>TIADA DATA<%--${actionBean.permohonanRujukanLuar.tarikhRujukan}--%>&nbsp;
        </tr>
        <tr>
            <label for="Trh_Surat_ Memohon">Tarikh Saman Pemula/Saman Dalam Kamar :</label>TIADA DATA<%--${actionBean.permohonanRujukanLuar.tarikhRujukan}--%>&nbsp;
        </tr>
        <tr>
            <label for="Maksud_Pengambilan">Masa Saman Pemula/Saman Dalam Kamar :</label>TIADA DATA<%--${actionBean.permohonan.sebab}--%>&nbsp;
           <%--<s:textarea name="permohonan.sebab" rows="4" cols="35" --%>
        </tr>
            <tr>
                 <label for="Tanah Asal">Lokasi Saman Pemula/Saman Dalam Kamar :</label>TIADA DATA<%--${actionBean.permohonanPengambilan.selepasPengambilan}--%>&nbsp;
            </tr>
            <tr>
                 <label for="Tanah Asal">Tarikh Ikrar :</label>TIADA DATA<%--${actionBean.permohonanPengambilan.selepasPengambilan}--%>&nbsp;
            </tr>



      </c:if>

            
         </fieldset>
</div>
</s:form>

