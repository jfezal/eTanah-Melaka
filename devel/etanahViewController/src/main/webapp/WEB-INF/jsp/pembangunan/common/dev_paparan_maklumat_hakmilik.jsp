<%-- 
    Document   : dev_paparan_maklumat_hakmilik
    Created on : Jan 13, 2010, 2:20:52 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan
            </legend>
            <p align="center"><label></label>

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/common/dev_maklumat_hakmilik" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column property="hakmilik.maklumatAtasTanah" title="Maklumat Atas Tanah"/>
                </display:table>

                <br>


            </p>

        </fieldset>
    </div>

     <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Maklumat Warta</legend>
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="malumatWarta" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column property="idHakmilik" title="ID Hakmilik"/>
                            <display:column property="noLembaranPiawai" title="No Lembaran Piawai"/>
                            <display:column property="jenisTanah" title="Jenis Tanah"/>
                            <display:column property="kedudukanTanah" title="Kedudukan Tanah"/>
                            <display:column property="noWarta" title="No Warta"></display:column>
                            <display:column property="tarikhWarta" title="Tarikh Warta"></display:column>
                    </display:table>
                    <br>


                </p>
            </fieldset>
        </div>
</s:form>
