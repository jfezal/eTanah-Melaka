<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>HASIL</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Kemaskini Hakmilik Kategori</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">

        function filterDaerah(kodDaerah, frm) {
                 //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
                 var url = '${pageContext.request.contextPath}/hasil/senarai_hakmilik_hasil?penyukatanBPM&kodDaerah=' + kodDaerah;
                 frm.action = url;
                 frm.submit();

               }

    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.hasil.SenaraiKemaskiniKatPemilikActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Parameter </legend>

                <br />
                <c:if test="${actionBean.kodCaw eq '00'}">
                <p>
                    <label>Daerah :</label>
                    <stripes:select name="kodDaerah" style="width:400px" id="_kodDaerah" onchange="filterDaerah(this.value,this.form);">
                        <stripes:option value="">Sila Pilih</stripes:option>
                        <stripes:options-collection collection="${actionBean.senaraiKodDaerah}" value="kod" label="nama"/>                    
                    </stripes:select>
                </p>
                </c:if>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <stripes:select name="kodBPM" id="_kodBPM">
                        <stripes:option value="">Sila Pilih</stripes:option>
                        <stripes:options-collection collection="${actionBean.listBandarPekanMukim}" value="kod" label="nama"/>
                    </stripes:select>
                </p>

                <p>
                    <label>Kategori Tanah :</label>
                    <stripes:select style="text-transform:uppercase" name="kodKatTanah">
                        <stripes:option value="">Sila Pilih</stripes:option>
                        <stripes:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                    </stripes:select>
                </p>
<p>
                    <label>Jumlah Carian :</label>
                    ${actionBean.count}
                </p>
                <br />
                <p>
                    <label>&nbsp;</label>  
                    <stripes:submit name="cari" value="Cari" class="btn"/>

                </p>
                <br />
            </fieldset>
            <fieldset class="aras1">
                <legend>Senarai Hakmilik </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiView}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <display:column title="Tarikh Daftar" property="trhDaftar"/>
                        <display:column title="Bandar/Pekan/Mukim" property="bpmNama"/>
                        <display:column title="Kategori Tanah" property="katgTanah"/>
                        <display:column title="Jumlah Pemilik" property="jumPemilik"/>
                        <display:column title="Senarai Pemilik" property="senaraiPemilik"/>
                        <display:column title="Pilih Kategori" style="width:18%">
                          <input type="hidden" name="idHakmilik" value="${line.idHakmilik}"/>

                            <stripes:select style="text-transform:uppercase" name="katPemilik">
                                <stripes:option value="">Sila Pilih</stripes:option>
                                <stripes:options-collection collection="${actionBean.senaraiKodKatPemilik}" label="nama" value="kod" />
                            </stripes:select>
                        </display:column>

                    </display:table>
                </p>
            </fieldset>
            <p>
                <label>&nbsp;</label>
                <p>
                    <label>&nbsp;</label>  
                    <stripes:submit name="simpan" value="Simpan" class="btn"/>

                </p>
            </p>
        </div>
    </stripes:form>
</div>


