
<%-- Document   : mukim_indeks_charting_akhir
     Created on : April 13, 2011, 12:07:10 PM
     Author     : sitifariza.hanim--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">
     $(document).ready( function() {


        var len = $(".popup").length;
        <%--alert("len indeks "+len);--%>

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                <%--alert("id hakm"+$(this).text());--%>
                 window.open("${pageContext.request.contextPath}/penguatkuasaan/mukim_indek?viewHakmilikBaru&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });

            $('.hakmilik2'+i).click( function() {
                <%--alert("id hakm"+$(this).text());--%>
                 window.open("${pageContext.request.contextPath}/penguatkuasaan/mukim_indek?viewHakmilikAsal&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

    });

    function ReplaceAll(Source,stringToFind,stringToReplace){
    var temp = Source;
    var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
       <%--alert(temp);--%>
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        <%--var stageId = "g_charting_ft";--%>
         // replace " " with "_"

         strNama = ReplaceAll(strNama," ","_");
         strJawatan = ReplaceAll(strJawatan," ","_");
         strStageID = ReplaceAll(strStageID," ","_");
         alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
        <%--
        alert ("stageid:" + stageId);--%>
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("etanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }

</script>


<s:form beanclass="etanah.view.penguatkuasaan.MukimIndeksActionBean" name="form">

            <s:errors/>
    <s:messages/>
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemaskini Mukim Indeks
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikSblm}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil">${line_rowNum}</display:column>

                    <display:column property="hakmilik.idHakmilik" title="Hakmilik Baru" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilikSebelum" title="Hakmilik Lama" class="popup hakmilik2${line_rowNum}" style="vertical-align:baseline"/>
                </display:table>
                <table>
                    <tr>
                        <td align="right">
                             <s:button name="chartingAkhir" id="chartingAkhir" value="Charting Akhir" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;

                        </td>
                    </tr>
                </table>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>