<%-- 
    Document   : jana_b4k
    Created on : Aug 15, 2017, 2:46:12 AM
    Author     : john
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">

function perincianHakmilik(idHakmilikInduk) {
//        alert(idHakmilikInduk);
        window.open("${pageContext.request.contextPath}/strata/jana_cukai_petak?perincianHakmilik&idHakmilikInduk=" + idHakmilikInduk, "eTanah",
                "status=0,location=0,menubar=0,width=1000,height=1000");
    }
    function cc(a, f) {
        var form = $(f).formSerialize();
        alert(a);

        var daerah;
        var url = '${pageContext.request.contextPath}/strata/kemaskiniskim?filterMukim&daerah=' + a + '&';
        window.location = url + form;
    }
</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.strata.cukaipetak.SenaraiEndorsanB4kCukaiPetakActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Skim Strata
            </legend>
            <p><label>Id Hakmilik</label> <s:text name="idHakmilik" value="idHakmilik" size="50"/> </p>
            <br>
            <p>
                <label>Daerah :</label>
                <%--<s:select name="daerah" id="Daerah"  onchange="cc(this.value,this.form);">--%>
                <s:select name="daerah" id="Daerah">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.listKodDaerah}" value="kod" label="nama" />                    
                </s:select>
            </p>
            <br>

            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="bpm" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.listBPM}" value="kod" label="nama"/>
                </s:select>
            </p>
            <br>
            <p><label>&nbsp;</label> <s:submit class="btn" name="cari" value="Cari"/>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Skim Strata
            </legend>
            <br/>
            <s:hidden name="idPlot" />
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.senaraiSkim}"
                               id="line" pagesize="20" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/strata/jana_b4k_cukai_petak"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">                    
                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="ID Hakmilik Master">
                         <a href="#" onclick="perincianHakmilik('${line.idHakmilikInduk}');">
                            ${line.idHakmilikInduk}
                        </a>
                        <input type="hidden" name="id" value="${line.id}"/>
                    </display:column>
                    <display:column title="Syarat Nyata Master" >
                        ${line.syaratNyataMaster}
                    </display:column>
                    <display:column title="BPM Master" >
                        ${line.bpmMaster}
                    </display:column>
                    
                </display:table>
            </div>

        </fieldset>            

    </div>
</s:form>