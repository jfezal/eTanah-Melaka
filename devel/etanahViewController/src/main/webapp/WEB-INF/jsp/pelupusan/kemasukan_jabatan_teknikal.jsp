<%-- 
    Document   : kemasukan_jabatan_teknikal
    Created on : Feb 2, 2010, 1:48:16 PM
    Author     : muhammad.amin
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">

    function addRow(){
        var rowNo = $('table#line1 tr').length;
        $('table#line1 > tbody').append("<tr id='x"+rowNo+"' class='x'>\n\
                <td class='rowNo'>"+rowNo+"</td>\n\
                <td><select name=jabatanArray style=width:550px>"+getOption()+"</select></td><td><input type=text name=ulasanArray /></td>\n\
                <td>&nbsp&nbsp&nbsp&nbsp<img alt='Klik Untuk Hapus' border=0 src=${pageContext.request.contextPath}/images/not_ok.gif class=rem   id=rem_${line_rowNum} onmouseover=this.style.cursor='pointer';></td></tr>");
    }

    function getOption(){
        var option='';
        var senaraiJabatan = document.getElementById('jabatan');
        for (var i = 0; i < senaraiJabatan.options.length; ++i){
            option = option + '<option value='+senaraiJabatan.options[i].value+'>'+senaraiJabatan.options[i].text+'</option>';
        }
        return option;
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Jabatan Teknikal</legend>
            <c:if test="${edit}">
                <div class="content" align="center">
                    <display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth">
                        <display:column title="No">
                            ${line1_rowNum}
                        </display:column>
                        <display:column title="Jabatan">
                            <s:select name="jabatanArray" id="jabatan" style="width:550px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tempoh Hari"><s:text value="${line1.ulasan}" name="ulasanArray"/></display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                            </div>
                        </display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="pilih" id="pilih" onclick="addRow();"/>&nbsp;
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
            </c:if>
        </fieldset >
    </div>
</s:form>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript">
    function addJabatan(){
        window.open("${pageContext.request.contextPath}/pelupusan/jabatan_teknikal?showTambahJabatan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=950,height=700");
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Jabatan Teknikal</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column  title="Jabatan"/>
                    <display:column  title="Bilangan Hari"/>

                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${line_rowNum}' onclick="remove('${line.idPermohonanPihak}')">
                        </div>
                    </display:column>

                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addJabatan();"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>--%>
