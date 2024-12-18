<%-- 
    Document   : cabut_undi_permohonan
    Created on : Sep 3, 2013, 10:58:49 PM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    $(document).ready( function(){

        var kodNegeri = $('#kodNegeri').val();
        var stageId = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        //stageId = "qt_ft";
        //alert(kodNegeri);
        //alert(stageId);
       // alert(kodUrusan);
        if(kodNegeri == '05' && (stageId == 'qt_ft')){
            if(kodUrusan == 'PBMT'){
                if(${actionBean.permohonan.catatan eq 'K'}){
                    $('#page_id_4').show();
                    //alert("show");
                }else{
                    $('#page_id_4').hide(); 
                    alert("Skrin ini untuk permohonan berkelompok sahaja");
                   //  $('#page_id_3').show();
                }
            } 
        }

    });
    function openFrame(type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/undian_kelompok?openFrame"
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshUndianPermohonanBerkelompok(type){
        var url = '${pageContext.request.contextPath}/pelupusan/undian_kelompok?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
    
    function editMohonHakmilik(idMohonHakmilik,type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/undian_kelompok?editDetails&idMH="+idMohonHakmilik
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.UndianPermohonanBerkelompokActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <c:if test="${actionBean.kelompok eq true}">         
        <div class="subtitle" align="center" id="senaraiHakmilikLulus">
            <fieldset class="aras1">
                <legend>Senarai Undian Plot Untuk Permohonan Individu</legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/undian_kelompok">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No Plot">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Plot" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                    </c:if>
                    <display:column title="Tindakan">
                        <c:if test="${actionBean.disRekodKeputusanController.sKelulusan}">
                            <a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            </c:if>
                        </display:column>
                    </display:table>        
                <br>        
            </fieldset>
        </div>             
    </c:if>
</s:form>
