<%--
    Document   : senaraiMenuItem
    Created on : Jun 7, 2011, 3:53:29 PM
    Author     : nurashidah.rosman
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function editMenu(frm, val) {
        var url = '${pageContext.request.contextPath}/uam/menu?viewMenu&idMenu='+val;
        frm.action = url;
        frm.submit();
    }

    function capitalizeEachWord(element) {
        //change string to capitalize each word format..
        val = element.value;
        newVal = '';
        val = val.split(' ');
        for(var c=0; c < val.length; c++) {
            newVal += val[c].substring(0,1).toUpperCase() +
                val[c].substring(1,val[c].length) + ' ';
        }
        element.value = newVal;
    }

    function removeMenu(frm, val) {
        if(confirm('Adakah anda pasti untuk hapus menu ini?')) {
            var url = '${pageContext.request.contextPath}/uam/senarai_menu?deleteMenu&idMenu='+val;
            frm.action = url;
            frm.submit();
        }
    }

</script>

<s:form beanclass="etanah.view.uam.SenaraiMenuItemActionBean" name="senaraiMenuItemForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Menu
            </legend>
            <p>
                <font size="2" color="Red">Perhatian :</font> Sila masukkan Tajuk Menu untuk membuat carian.
            </p>
            <p>
                <label for="tajuk"><font color="red">*</font>Tajuk Menu :</label>
                <s:text name="tajuk" onchange="capitalizeEachWord(this);"/>
                <s:submit name="searchMenuItem" id="search" value="Cari" class="btn"/>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>
                Senarai Menu
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiMenuItem}" id="line" class="tablecloth" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/uam/senarai_menu">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="tajuk" title="Tajuk" style="vertical-align:baseline"/>
                    <display:column property="uri" title="URI" style="vertical-align:baseline"/>
                    <display:column property="turutan" title="Turutan" style="vertical-align:baseline"/>
                    <display:column title="Aktif" style="vertical-align:baseline">
                        <c:if test="${line.aktif eq 'Y'}">Ya</c:if>
                        <c:if test="${line.aktif eq 'T'}">Tidak</c:if>
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editMenu(document.forms.senaraiMenuItemForm, '${line.idMenu}')"/>
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeMenu(document.forms.senaraiMenuItemForm, '${line.idMenu}')"/>
                        </div>
                    </display:column>
                 
                </display:table>
            </div>
        </fieldset>

    </div>
</s:form>