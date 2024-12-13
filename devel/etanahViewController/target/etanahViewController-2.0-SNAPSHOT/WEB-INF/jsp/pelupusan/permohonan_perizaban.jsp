<%-- 
    Document   : permohonan_perizaban
    Created on : Jun 1, 2010, 7:33:09 PM
    Author     : sitifariza.hanim
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.PerizabanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Maklumat Pemohon Perizaban </legend>
            <p><label>&nbsp;</label><br><br>
                <label>Tujuan Perizaban :</label>
                 <s:select name="permohonan.sebab" style="width:495px" id="sebab" value="${actionBean.permohonan.sebab}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="nama" />
                 </s:select>
            </p>
            <p>
                <label>Nama Agensi/Badan Kerajaan :</label>
                <s:text name="namaAgensi" size="80"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="namaAgensi2" size="50"/>
            </p>
            <p>
                <label>Alamat Berdaftar :</label>
                <s:text name="alamat1" size="50"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2" size="50"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat3" size="50"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat4" size="50"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
             <p>
                <label>Perihal Kawasan :</label>
                <s:text name="perihalKawasan" size="50"/>
            </p>
             <p>
                <label>Pegawai Pengawal :</label>
                <s:text name="pegawaiPengawal" size="50"/>
            </p>
             <%--<p>
                <label>Penyelenggara :</label>
                <s:text name="penyelenggaraan" size="50"/>
            </p>--%>
            <label>&nbsp;</label>
            <p>&nbsp;&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>

