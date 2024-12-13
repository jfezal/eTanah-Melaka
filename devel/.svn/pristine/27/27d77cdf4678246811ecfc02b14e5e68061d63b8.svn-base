<%-- 
    Document   : peranan
    Created on : Jun 30, 2011, 3:41:52 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function viewPengguna(id){
        window.open("${pageContext.request.contextPath}/uam/peranan?viewPengguna&kod="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }

    function popup(url)
    {
        params  = 'width=1100';
        params += ', height=1000';
        params += ', top=200, left=100';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=yes';
        params += ', fullscreen=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
</script>
<div id="all">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.uam.AllPerananActionBean" id="user_peranan">
        <s:messages/>
        <s:errors/>
        <div class="subtitle displaytag">
            <fieldset class="aras1">
                <legend>Carian Kumpulan Pengguna</legend>
                <p>
                    <font color="red">PERINGATAN :</font>  Sila Masukan Maklumat Berikut.
                </p>
                <p>
                    <label>Peranan :</label>
                    <s:select name="perananUtama" style="width:370px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPeranan}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <div align="right">
                    <s:submit name="searchPeranan" value="Cari" class="btn" />
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('user_peranan');"/>
                </div>
            </fieldset >
            <br>
            <c:if test="${actionBean.flag}">
                <fieldset class="aras1">
                    <legend>Senarai Kumpulan Pengguna</legend>
                    <p>
                        Sila klik pada perihal untuk melihat senarai pengguna
                    </p>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPeranan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/uam/peranan">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                            <display:column property="kod" title="Kod Peranan" style="vertical-align:baseline"/>
                            <display:column title="Perihal" style="vertical-align:baseline">
                                <a href="#" onclick="viewPengguna('${line.kod}');return false;">${line.nama}</a>
                            </display:column>
                            <display:column property="kumpBPEL" title="Kumpulan BPEL" style="vertical-align:baseline"/>
                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
            <br>
            <fieldset class="aras1">
                <legend>
                    Senarai Laporan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <div align="center">
                    <table class="tablecloth">
                        <tr style="width: 100%">
                            <th>Bil</th>
                            <th>Laporan</th>
                            <th>Papar</th>

                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiPerananKump}" var="report">
                            <tr>
                                <td>${count}
                                </td>
                                <td>${report}
                                </td>
                                <td><div align="center">
                                        <p align="center">
                                            <img title="Klik Untuk Papar" alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="popup('${pageContext.request.contextPath}/uam/peranan/requestParam?namaReport=${actionBean.senaraiPerananKumpRN[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </div>
                                </td>
                            </tr>
                            <c:set value="${count +1}" var="count"/>
                        </c:forEach>
                        </tr>
                    </table>
                </div>
                <br>
            </fieldset>    
        </div>
    </s:form>
</div>