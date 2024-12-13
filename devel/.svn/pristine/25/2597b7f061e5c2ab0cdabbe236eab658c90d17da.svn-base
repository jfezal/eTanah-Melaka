<%-- 
    Document   : latarbelakang
    Created on : May 3, 2010, 11:28:08 AM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>

<%--<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>--%>

    <s:form beanclass="etanah.view.stripes.pelupusan.PermohonanLPSPTActionBean">
        <div class="subtitle" id="caw">
            <fieldset class="aras1">
        <legend>Butir - Butir Suami / Isteri</legend>
                <p><label>Nama :</label>
                    <s:text name="name" size="40"/>
                </p>
                <p> <label>Alamat Tetap :</label>
                    <s:text name="alamat1" size="40"/>
                </p>
                <p> <label>&nbsp;</label>
                    <s:text name="alamat2" size="40"/>
                </p>
                <p> <label>&nbsp;</label>
                    <s:text name="alamat3" size="40"/>
                </p>
                <p> <label>&nbsp;</label>
                    <s:text name="alamat4" size="40"/>
                </p>
                <p> <label>Poskod:</label>
                    <s:text name="poskod" size="15"/>
                </p>
                <p> <label>Bandar:</label>
                    <s:text name="bandar" size="40"/>
                </p>
               <p> <label>Negeri:</label>
                      <s:select name="pihak.negeri.kod" id="negeri" style="width:200px">
                      <s:option value="0">Sila Pilih</s:option>
                      <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                      </s:select>
               </p>
                <p><label>Tarikh Lahir :</label>
                   <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                </p>
                <p> <label>Tempat Lahir :</label>
                    <s:text name="tmptlahir" size="40"/>
                </p>

                <p> <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="15" onkeyup="dodacheck(this.value);"
                            onblur="doUpperCase(this.id)<s:;doCheckUmur(this.value, this.id);"/> 
                </p>
                 <p>
                    <label>Warna:</label>
                    <s:text name="warna" size="15" maxlength="12"/>
                </p>

                <p> <label>Kewarganegaraan :</label>
                    <s:select name="pihak.wargaNegara.kod" style="width:200px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p> <label>Pekerjaan :</label>
                    <s:text name="pekerjaan" size="40"/>
                </p>
                <p> <label for =" Umur">Umur :</label>
                    <s:text name="umur" size="15" maxlength="3"/>
                </p>
                <p> <label>Pendapatan Bulanan (RM):</label>
                    <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
               <%-- <p> <label>&nbsp;</label>
                    <s:text name="alamat6" size="40"/>
                </p>
                <p> <label>&nbsp;</label>
                    <s:text name="alamat7" size="40"/>
                </p>
                <p> <label>&nbsp;</label>
                    <s:text name="alamat8" size="40"/>
                </p>
                <p> <label>Poskod:</label>
                    <s:text name="poskod1" size="5"/>
                </p>
                <p> <label>Bandar:</label>
                    <s:text name="bandar1" size="40"/>
                </p>
                <p> <label>Negeri:</label>
                      <s:select name="pihak.negeri.kod" id="negeri">
                      <s:option value="0">Sila Pilih</s:option>
                      <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                      </s:select>
                </p>
                <p> <label>No. Telefon:</label>
                    <s:text name="telefon" size="3"/><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><s:text name="telefon1" size="8"/>
                    <s:text name="telefon" maxlength="12"/>
                </p>
                <p> <label>No. Faksimili:</label>
                    <s:text name="fax" size="3"/><label></label><s:text name="fax1" size="8"/>
                   <s:text name="fax" maxlength="12"/>
                </p>
                <p> <label>Umur :</label>
                    <s:text name="permohonanPihak.umur" size="10" maxlength="3"/>
                </p>
                <p><label>Tarikh Lahir :</label>
                   <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                </p>
                 <p><label>Tempat Lahir :</label>
                    <s:text name="pihak.tempatLahir" size="40"/>
                 </p>
                 <p><label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p> <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="12" onkeyup="dodacheck(this.value);"
                            onblur="doUpperCase(this.id)<s:;doCheckUmur(this.value, this.id);"/> <label>Warna:</label>
                    <s:text name="warna" maxlength="12"/>
                </p>
                <p> <label>Jantina :</label>
                    <s:text name="jantina" size="20"/>
                </p>
                <p><label>Status Perkahwinan :</label>
                   <s:text name="statusperkahwinan" size="40" maxlength="10"/>
                </p>
                <p> <label>Kewarganegaraan :</label>
                    <s:select name="pihak.wargaNegara.kod" style="width:200px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p> <label>Pekerjaan :</label>
                    <s:text name="permohonanPihak.pekerjaan" size="40"/>
                </p>
                <p> <label>No Pendaftaran Syarikat:</label>
                    <s:text name="noreg" size="30"/>
                </p>
                <p> <label>Pendapatan Bulanan (RM):</label>
                    <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p> <label>Modal Berbayar (RM):</label>
                    <s:text name="modalberbayar" size="40"/>
                </p> --%>
                <br/>
                <p> <label>&nbsp;</label>
                    <s:button class="btn" value="Kembali" name="new" id="new" onclick="addPemohon();"/>&nbsp;
                  <label>&nbsp;</label>  <s:button class="btn" value="Simpan" name="new" id="new" onclick="addPemohon();"/>&nbsp;
                </p>


        </fieldset>
        </div>
 </s:form>
