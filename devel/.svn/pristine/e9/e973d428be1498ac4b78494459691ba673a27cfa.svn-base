<%-- 
    Document   : pembetulan_wakil
    Created on : Sep 23, 2020, 1:46:04 PM
    Author     : zipzap
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.jtimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">





</script>           
<div class="subtitile">

    <s:errors/>
    <s:messages/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.PembetulanWakilActionBean">


        <fieldset class="aras1">
            <legend>Carian Id Wakil</legend>
            <p>
                <label><em>*</em>ID Wakil :</label>
                <s:text name="idPermohonan" id="idPermohonan"  onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="cari" id="save" value="Cari" class="btn"/>
            </p>

            <br/>
            <legend>Pemberi</legend>
            <div class="content" align="center">
                <table class="tablecloth" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Bil</th>
                            <th>Jenis Medan</th>
                            <!--<th>Maklumat Lama</th>-->
                            <th>Maklumat Baru</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd">
                            <td>1.</td>
                            <td>Nama</td><s:hidden name="idHakmilik"/><s:hidden name="namaLama" value="${actionBean.wakilPemberi.nama}"/>
                            <td>
                                <s:textarea name="namaPemberi" id="namaPemberi" onkeyup="this.value=this.value.toUpperCase()" rows="5" cols="50" value="${nama}"/>
                            </td>
                        </tr>
                        <tr class="even">
                            <td>2.</td>
                            <td>Jenis Pengenalan</td>
                            <td>
                                <s:select name="jenisPengenalanPemberi" id="jenisPengenalanPemberi" value="${kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr class="odd">
                            <td>3.</td>
                            <td>No Pengenalan</td>
                            <td><s:text name="noPengenalanPemberi" id="noPengenalanPemberi" onkeyup="this.value=this.value.toUpperCase();" value="${noPengenalanPemberi}"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
                           <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}" />
            <center><s:submit name="simpanPemberiNew" id="simpanPemberiNew" value="Simpan" class="btn"/></center>
            <br>
        </fieldset>
    </s:form>
</div>
