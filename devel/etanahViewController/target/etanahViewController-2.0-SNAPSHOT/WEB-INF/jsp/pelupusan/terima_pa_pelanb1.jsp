<%-- 
    Document   : terima_pa_pelanb1
    Created on : 12-Dec-2012, 12:25:48
    Author     : TOSHIBA
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
    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        var strStageID2 = "g_kemaskini_hakmilik";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
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
    
    function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        //alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_terima_pa_b1";
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        //alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.pelupusan.TerimaPAPelanB1ActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan Ukur
            </legend>
            <br/>
            <table border="0">

                <tr>

                    <td>Nombor PT :</td>
                    <td>
                        <c:if test="${actionBean.noPt.noPt ne null}">
                            ${actionBean.noPt.noPt}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Nombor PU :</td>
                    <td>   
                        ${actionBean.noPU}
                    </td>
                </tr>
                <tr>
                    <td>Luas Diluluskan :</td>
                    <td><c:if test="${actionBean.noPt.luasDilulus ne null}">
                            ${actionBean.noPt.luasDilulus} ${actionBean.noPt.kodUOM.nama}
                        </c:if></td>
                </tr><tr>

                    <td>Luas Diukur :</td>
                    <td>
                        ${actionBean.luas}
                    </td>
                </tr> 
            </table>
        </fieldset>
                    
                       <s:button name="semakTerima" id="semakTerima" value="Semak Terima PA/B1" class="longbtn"  onclick="RunProgram3('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>
    </div>
</s:form>

