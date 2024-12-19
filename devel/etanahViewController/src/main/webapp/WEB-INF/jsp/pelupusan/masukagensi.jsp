<%-- 
    Document   : terima_pa_b1
    Created on : Nov 4, 2012, 7:46:16 PM
    Author     : Navin
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        <%--strIDStage  = "g_terima_pa_b1";
        var stageId = "g_terima_pa_b1";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        --%>
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
    
    function val1()
    {   if($('#agensi').val() == "")
        {   alert("Sila pilih Agensi.");
            return false;
        }        
        return true;
    }
</script>


<s:form beanclass="etanah.view.stripes.pelupusan.AgensiActionBean" name="form">
<s:messages/>
                    <br><br>          
                <table border="0" align="center">

                    <tr>
                        <td>
                            <b>Agensi :</b>&nbsp;
                        </td>
                        <td>
                            <s:select name="agensi" id="agen">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodAgensi}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </td>
                    </tr>

                </table>
                    <br>
                    <p align="center">
                            <s:button name="SimpanAgensi" class="longbtn" value="Simpan" onclick="if(val1())doSubmit(this.form,this.name,'page_div')"/>
                </p>
</s:form>
