<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;

    }
    input, select{width:95%}
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.MaklumatSuratActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Surat
            </legend>
            <br/>
            <p><font color="black" size="2"><b>*Sila isi yang diperlukan sahaja.</b></font></p>
            <s:hidden name="idSurat"/>
            <table class="tablecloth" width="90%">
                <tr><th>Perkara</th><th>Maklumat Pemberi (Lama)</th><th>Maklumat Penerima (Baru)</th></tr>
                <tr><td style="width: 30%">Nama Pemberi :</td>
                    <td><s:text name="nama" readonly="readonly" size="40"/></td>
                    <td><s:text name="namaBaru" size="40" maxlength="300"/></td>
                </tr>
                <tr><td>Kod Pengenalan Pemberi :</td>
                    <td>
                        <s:select name="kodPengenalan" id="kodPengenalan" value="${actionBean.kodPengenalan}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                    <td>
                        <s:select name="kodPengenalanBaru" id="kodPengenalanBaru" value="${actionBean.kodPengenalanBaru}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                </tr>
                <tr><td>No Pengenalan Pemberi :</td>
                    <td><s:text name="noPengenalan" readonly="readonly"/></td>
                    <td><s:text name="noPengenalanBaru" maxlength="20"/></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr><td>Nama Penerima :</td>
                    <td><s:text name="namaPenerima" readonly="readonly" size="40"/></td>
                    <td><s:text name="namaPenerimaBaru" size="40" maxlength="250"/></td> 
                </tr>
                <tr><td>Kod Pengenalan Penerima :</td>
                    <td>
                        <s:select name="kodPengenalanPenerima" id="kodPengenalanPenerima" value="${actionBean.kodPengenalanPenerima}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                    <td>
                        <s:select name="kodPengenalanPenerimaBaru" id="kodPengenalanPenerimaBaru" value="${actionBean.kodPengenalanPenerimaBaru}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                </tr>
                <tr><td>No Pengenalan Penerima :</td>
                    <td><s:text name="noPengenalanPenerima" readonly="readonly"/></td>
                    <td><s:text name="noPengenalanPenerimaBaru" maxlength="20"/></td>
                </tr>
                <tr><td>Alamat Penerima :</td>
                    <td><s:text name="alamat1" readonly="readonly" size="40"/></td>
                    <td><s:text name="alamat1Baru" size="40" maxlength="60"/></td>
                </tr>
                <tr><td>&nbsp;</td>
                    <td><s:text name="alamat2" readonly="readonly" size="40"/></td>
                    <td><s:text name="alamat2Baru" size="40" maxlength="60"/></td>
                </tr>
                <tr><td>&nbsp;</td>
                    <td><s:text name="alamat3" readonly="readonly"/></td>
                    <td><s:text name="alamat3Baru" maxlength="60"/></td>
                </tr>
                <tr><td>&nbsp;</td>
                    <td><s:text name="alamat4" readonly="readonly"/></td>
                    <td><s:text name="alamat4Baru" maxlength="60"/></td>
                </tr>
                <tr><td>&nbsp;</td>
                    <td><s:text name="poskod" readonly="readonly"/></td>
                    <td><s:text name="poskodBaru" maxlength = "5"/></td>
                </tr>
                <tr><td>&nbsp;</td>
                    <td>
                        <s:select name="kodNegeri" value="${actionBean.kodNegeri}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </td>
                    <td>
                        <s:select name="kodNegeriBaru"  value="${actionBean.kodNegeriBaru}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
            </table>
            <div align="center">
                <s:submit name="simpanSurat" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
            </div>
        </fieldset>
    </div>
</s:form>