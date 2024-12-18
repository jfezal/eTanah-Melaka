<%-- 
    Document   : milik_tanah_mcl
    Created on : Jul 12, 2010, 4:08:34 PM
    Author     : afham
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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

<s:form beanclass="etanah.view.stripes.pelupusan.MilikTanahMCL_ActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>

                    <display:column title="Maklumat Atas Tanah"><s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah"/> </display:column>

                        
                </display:table>
            </div>
            <p align="center"><label></label>
                <br>
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>

        </fieldset>
    </div>
    
                </s:form>

<%-- <div class="subtitle">
        <center> <display:table class="tablecloth" name="" id="" requestURI="">
            <display:column title="ID NoHakMilik"></display:column>
            <display:column title="Nombor Lot/PT"></display:column>
            <display:column title="Luas"></display:column>
            <display:column title="Daerah"></display:column>
            <display:column title="Bandar/Pekan/Mukim"></display:column>
            </display:table> </center>


              <%-- <display:table class="tablecloth" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline"></display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000"/></display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>

                    <display:column title="Maklumat Atas Tanah"><s:textarea/></display:column>
</display:table>


                <center><s:submit name="" value="Simpan" class="btn"/></center>
                </div> --%>