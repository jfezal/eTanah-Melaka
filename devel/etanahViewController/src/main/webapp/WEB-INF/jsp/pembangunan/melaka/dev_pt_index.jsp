<%-- 
    Document   : dev_pt_index
    Created on : Sep 12, 2011, 6:52:22 PM
    Author     : NageswaraRao
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>
<script type="text/javascript">
// function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
//
//        var strNama2 = ReplaceAll(strNama," ","_");
//        var strJawatan2 = ReplaceAll(strJawatan," ","_");
//        var strStageID2 = "g_kemaskini_hakmilik";
//        var objShell = new ActiveXObject("WScript.Shell");
//        var sysVar = objShell.Environment("System");
//        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
//        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
//
//
//        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
//    }
    
    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {     
        strIDStage = "g_kemaskini_hakmilik";
        //alert(strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");      
        stageId = strIDStage;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");        
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
    </script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.pembangunan.IndexActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Fahrasat Pejabat Tanah
            </legend>
            <br/>
            <table border="0">

            <tr>
            <td>Nombor PT</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.noPt.noPt ne null}">
                ${actionBean.noPt.noPt}
                </c:if>           
                <c:if test="${actionBean.noPt.noPt eq null}">
                &nbsp; -
                </c:if>
            </td>
            </tr>
            
            <tr>
            <td>Nombor Syit</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.permohonanLaporanPelan.noLitho ne null}">
                ${actionBean.permohonanLaporanPelan.noLitho}
                </c:if>           
                <c:if test="${actionBean.permohonanLaporanPelan.noLitho eq null}">
                &nbsp; -
                </c:if>
            </td>
            </tr>
            
            <tr>
            <td>Luas Diluluskan</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.noPt.luasDilulus ne null}">
                ${actionBean.noPt.luasDilulus} ${actionBean.noPt.kodUOM.nama}
                </c:if>
                <c:if test="${actionBean.noPt.luasDilulus eq null}">
                &nbsp; -
                </c:if>
            </td>
            </tr>
            
            <tr>
            <td>Nombor Permohonan Ukur</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.permohonanUkur.noPermohonanUkur ne null}">
                ${actionBean.permohonanUkur.noPermohonanUkur}
                </c:if>          
                <c:if test="${actionBean.permohonanUkur.noPermohonanUkur eq null}">
                &nbsp; -
                </c:if>
            </td>
            </tr>
            
            <tr>
            <td>Nombor Lot Ukur</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.noLot ne null}">
                <fmt:formatNumber  pattern="00" value="${actionBean.noLot}"/>               
                </c:if>
                <c:if test="${actionBean.noLot eq null}">
                &nbsp; -              
                </c:if>
            </td> 
            </tr>
            
            <tr>
            <td>Nombor Hakmilik Sementara/LMS</td>
            <td>&nbsp;:&nbsp;</td>
            <td>
                <c:if test="${actionBean.noHakmilik ne null}">
                    ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} &nbsp;<fmt:formatNumber  pattern="00" value="${actionBean.noHakmilik}"/>    
                </c:if>
                <c:if test="${actionBean.noHakmilik eq null}">
                &nbsp; -   
                </c:if>
            </td>            
            </tr>
            
            </table>
            <br/><br/>
            <p align="center">          
            <s:button name="Charting" id="charting" class="longbtn" value="Kemaskini Hakmilik" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>         
        </fieldset>
    </div>
</s:form>