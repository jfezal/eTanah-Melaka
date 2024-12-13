<%-- 
    Document   : rekod_keputusan
    Created on : Nov 11, 2012, 12:38:05 AM
    Author     : Afham
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
    function openFrame(type){
        doBlockUI();
        var idHakmilik = $('#idHakmilik').val();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/consent/rekod_keputusan?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshRekodKeputusanV2(type){
        var url = '${pageContext.request.contextPath}/consent/rekod_keputusan?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/rekod_keputusan?reload&idHakmilik=' + val;
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

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        strIDStage = "g_charting_keputusan";
        alert ("stageid:" + strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
</script>

<s:form beanclass="etanah.view.stripes.consent.RekodKeputusanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="permohonanKertas.idKertas"/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <div class="subtitle" align="center">

        <div id="maklumat_permohonan">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
                <table class="tablecloth" border="0">
                    <tr>
                        <td>ID Permohonan / Perserahan :</td>
                        <td>${actionBean.permohonan.idPermohonan}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Urusan :</td>
                        <td>${actionBean.permohonan.kodUrusan.nama}&nbsp;</td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div id="keputusan_permohonan">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat 
                    <span style="float:right">
                        <c:if test="${actionBean.disRekodKeputusanController.mMesyuarat}">
                            <a onclick="openFrame('mMesyuarat');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <table class="tablecloth" border="0">
                    <tr>
                        <td>Bilangan Mesyuarat :</td>
                        <td>
                            <c:if test="${actionBean.permohonanKertas.nomborRujukan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanKertas.nomborRujukan}</font> </c:if>
                            <c:if test="${actionBean.permohonanKertas.nomborRujukan eq null}"> TIADA DATA </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Keputusan :</td>
                        <td>
                            <c:if test="${actionBean.permohonan.keputusan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonan.keputusan.nama}</font> </c:if>
                            <c:if test="${actionBean.permohonan.keputusan eq null}"> TIADA DATA </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Tarikh Bersidang :</td>
                        <td>
                            <c:if test="${actionBean.permohonanKertas.tarikhSidang ne null}"><s:format value="${actionBean.permohonanKertas.tarikhSidang}" formatPattern="dd/MM/yyyy"/>&nbsp;</td></c:if>
                        <c:if test="${actionBean.permohonanKertas.tarikhSidang eq null}"> TIADA DATA </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Tarikh Disahkan :</td>
                        <td>
                            <c:if test="${actionBean.permohonanKertas.tarikhSahKeputusan ne null}"><s:format value="${actionBean.permohonanKertas.tarikhSahKeputusan}" formatPattern="dd/MM/yyyy"/>&nbsp;</td></c:if>
                        <c:if test="${actionBean.permohonanKertas.tarikhSahKeputusan eq null}"> TIADA DATA </c:if>
                        </td>
                    </tr>
                </table>
            </fieldset>

        </div>
    </s:form>
