<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function openSalinanKepada(idPermohonan,kodDokumen){
        //        doBlockUI();
//        alert(kodDokumen);
//        alert("xxx");
        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormSalinan&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    
    function janaPermohonan(){
        var idKelompok = $('#idKelompok').val() ;
        if(confirm("Adakah anda pasti untuk jana permohonan?")) {
            var url = '${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?janaPermohonan&idKelompok='+idKelompok ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.refreshPermohonanKelompok(idKelompok);
            },'html');
        }
    }
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiSalinanKepadaDevActionBean" name ="utilitiSalinan" id ="utilitiSalinan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Permohonan :</label>
                        <s:text id="idPermohonan" name="idPermohonan"></s:text>
                    </p>
                    <p align="center">
                        <s:submit name="findKandungan" value="Cari" class="btn"/>&nbsp;
                        <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('utilitiSalinan')" />
                        <%--<s:button name="janaDokumen" id="janaDokumen" class="btn" value="Jana Dokumen x"/>--%>
                    </p>
            </fieldset>
        </div>

        <div class="content">
            <fieldset class="aras1">
                <legend>
                    Senarai Dokumen
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKandungan}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/utiliti/utilitiSalinan">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column property="dokumen.kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="dokumen.kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Tindakan">
                            <a onclick="openSalinanKepada('${actionBean.idPermohonan}','${line.dokumen.kodDokumen.kod}')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            </display:column>
                        </display:table>
                </div>
                
            <!--Button Jana-->            
            <c:if test = "${actionBean.idPermohonan != null}">
            <p align ="center">
                <s:submit name="janaDokumen" id="janaDokumen" class="btn" value="Jana Dokumen"/>
            </p>
            </c:if>
            </fieldset>           
        </div>
    </div> 
</s:form>
