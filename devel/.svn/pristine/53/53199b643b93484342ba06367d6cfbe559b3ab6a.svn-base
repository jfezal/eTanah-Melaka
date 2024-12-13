<%-- 
    Document   : keputusan_permohonan_terdahulu
    Created on : 24 April, 2013, 06:12 PM
    Author     : zabedah zainal
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?reload&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
        
    function reloadPT (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?reload&noPtSementara=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
        
    <%--  function openFrame(type){
          doBlockUI();
          var idHakmilik = $('#idHakmilik').val();
          var noPtSementara = $('#noPtSementara').val();
          //        alert(idHakmilik);
          window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?openFrame&idHakmilik="
              +idHakmilik+"&noPtSementara="+noPtSementara+"&type="+type, "eTanah",
          "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
          //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
      }--%>
    
    <%-- function refreshV2(type){
         var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
         $.get(url,
         function(data){
             $('#page_div').html(data);
         },'html');
         doUnBlockUI();
     }--%>
        
    <%-- function refreshV2ManyHakmilik(type,hakmilik){
         var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type+'&idHakmilik='+hakmilik;
         $.get(url,
         function(data){
             $('#page_div').html(data);
         },'html');
         doUnBlockUI();
     }--%>
    
         function ReplaceAll(Source,stringToFind,stringToReplace){
             var temp = Source;
             var index = temp.indexOf(stringToFind);
             while(index != -1){
                 temp = temp.replace(stringToFind,stringToReplace);
                 index = temp.indexOf(stringToFind);

             }
             return temp;
         }
    
         function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
		
             var strNama2 = ReplaceAll(strNama," ","_");
             var strJawatan2 = ReplaceAll(strJawatan," ","_");
             var strStageID2 = "g_charting_keputusan";
             var objShell = new ActiveXObject("WScript.Shell");
             var sysVar = objShell.Environment("System");
            
             objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
         }
         
         function RunProgram1(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
		
             var strNama2 = ReplaceAll(strNama," ","_");
             var strJawatan2 = ReplaceAll(strJawatan," ","_");
             var strStageID2 = "g_charting_semak";
             var objShell = new ActiveXObject("WScript.Shell");
             var sysVar = objShell.Environment("System");
            
             objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
         }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanPermohonanTerdahuluActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <div class="subtitle displaytag" align="center">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend>Keputusan Permohonan Terdahulu</legend>

                <display:table class="tablecloth" name="${actionBean.listpermohonanTanahTerdahulu}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                    <s:hidden name="" class="${line1_rowNum -1}" value="${line1.permohonanManualSemasa.idMohonManual}"/>
                    <display:column title="No">${line1_rowNum}</display:column>

                    <display:column title="ID Permohanan/No Fail"> 
                        <c:if test="${line1.permohonanTerdahulu ne null}">
                            ${line1.permohonanTerdahulu.idPermohonan}
                        </c:if>
                        <c:if test="${line1.permohonanTerdahulu eq null}">
                            ${line1.noFail}
                        </c:if>
                    </display:column>

                    <display:column title="Urusan">
                        <c:if test="${line1.permohonanTerdahulu ne null}">
                            ${line1.permohonanTerdahulu.kodUrusan.nama}
                        </c:if>
                        <c:if test="${line1.permohonanTerdahulu eq null}">
                            Tiada
                        </c:if>
                    </display:column>

                    <display:column title="Status Keputusan">
                        <c:if test="${line1.permohonanTerdahulu ne null}">
                            ${line1.statusKeputusan}
                        </c:if>

                        <c:if test="${line1.permohonanTerdahulu eq null}">
                            <font color="red">**</font>Keputusan perlu dirujuk secara manual
                        </c:if>
                    </display:column>
                </display:table> <br/>
            </fieldset> <br>
        </div>
        <c:choose>
            <c:when test="${actionBean.stageId eq 'g_charting_kpsn_T1'}">
                <div class="subtitle">
                    <fieldset class="aras1" id="locate">
                        <legend>
                            Kegunaan GIS
                        </legend>


                        <p>
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>

                    </fieldset> <br>
                </div>
            </c:when>
            <c:when test="${actionBean.stageId eq 'semak_charting_kpsn_T1'}">
                <div class="subtitle">
                    <fieldset class="aras1" id="locate">
                        <legend>
                            Kegunaan GIS
                        </legend>


                        <p>
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram1('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>

                    </fieldset> <br>
                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
        

    </div>
</s:form>