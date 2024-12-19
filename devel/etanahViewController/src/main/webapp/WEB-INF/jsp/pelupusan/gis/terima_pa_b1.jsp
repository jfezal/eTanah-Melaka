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
    $(document).ready(function() {
        $('#pilih').attr("disabled", "true");
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJTK'}">
            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 ne null && actionBean.hakmilikPermohonan.luasDiluluskan ne null}">
                var luasB1 = ${actionBean.hakmilikPermohonan.luasPelanB1};
                var luasAsal = ${actionBean.hakmilikPermohonan.luasDiluluskan};
                var luasDitolak = luasB1 - luasAsal;
                var totalKiraan = (luasDitolak / luasAsal) *100;
                var numb = precision( parseFloat(totalKiraan) , 2 );
                document.getElementById("totalKira").innerHTML = numb+"%";
            </c:if>    
    </c:if>
      
        }); //END OF READY FUNCTION
    
        function precision( number , precision ){
            var prec = Math.pow( 10 , precision );
            return Math.round( number * prec ) / prec;
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

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            $('#pilih').removeAttr("disabled");
    <%--strIDStage  = "g_terima_pa_b1";
    var stageId = "g_terima_pa_b1";
    alert("nama:" + strNama);
    alert ("jawatan:" + strJawatan);
    alert ("stageid:" + strIDStage);
    alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
    --%>
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
            //            self.openFrame();
        }
        
        function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            $('#pilih').removeAttr("disabled");
            strIDStage  = "g_terima_pa_b1";
            //            var stageId = "g_terima_pa_b1";
            //            alert("nama:" + strNama);
            //            alert ("jawatan:" + strJawatan);
            //            alert ("stageid:" + strIDStage);
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
            //            self.openFrame();
        }
        
        function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            //            $('#pilih').removeAttr("disabled");
            strIDStage  = "g_terima_pw";
            //            var stageId = "g_terima_pa_b1";
            //            alert("nama:" + strNama);
            //            alert ("jawatan:" + strJawatan);
            //            alert ("stageid:" + strIDStage);
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
            //            self.openFrame();
        }
    
        function openFrame(){
            doBlockUI();
            window.open("${pageContext.request.contextPath}/pelupusan/gisIntegration?kemaskiniData", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200,scrollbars=yes");
        }
    
        function refreshTerimaPAB1(){
            var url = '${pageContext.request.contextPath}/pelupusan/gisIntegration?showForm8';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
            doUnBlockUI();
        }
        
        function selectRadioBox()
        {
            //        NoPrompt();
            if(confirm("Adakah anda pasti untuk dikemaskini?")) {
                var url = '${pageContext.request.contextPath}/pelupusan/gisIntegration?updateGISPelanB1' ;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod telah berjaya di kemaskini") ;
                    self.refreshTerimaPAB1() ;
                    //                self.close() ;
                    //                self.opener.refreshTerimaPAB1() ;
                },'html');
            }
        }
    
        function valPW()
        {   if($('#noPW').val() == "")
            {   alert("Sila masukkan No PW.");
                return false;
            }
            return true;
        }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean" name="form">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" >
        <fieldset class="aras1">
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <legend>Maklumat PA/B1</legend>             
                </c:when>
                <c:otherwise>
                    <legend>Maklumat PW</legend>   
                </c:otherwise>
            </c:choose>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                <c:if test="${actionBean.stageId eq 'g_terima_pa_b1'}">        
                    <p style="color:red" align="center">
                        *Sila klik pada butang kemaskini untuk mengemaskini no lot dan luas pelan B1 selepas tekan butang Terima PA/B1<br/>
                    </p>
                </c:if>


                <table border="0" class="tablecloth" align="center">
                    <tr>
                        <th>Luas Lulus (Asal)</th>
                        <th>Luas Ukur Halus (JUPEM)</th>
                        <th>Perbezaan</th>
                        <th>Peratusan Perbezaan (%)</th>
                    </tr>
                    <tr>
                        <td><b><div align="center"><s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama}</div></b> </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 eq null}">
                                <b><div style="color:red" align="center">Belum Dikemaskini</div></b>
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 ne null}">
                                <b><div align="center"><s:format value="${actionBean.hakmilikPermohonan.luasPelanB1}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasPelanB1Uom.nama}</div></b>
                            </c:if>                            
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 eq null}">
                                <b><div style="color:red" align="center">Belum Dikemaskini</div></b>
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 ne null}">
                                <b><div align="center"><s:format value="${actionBean.hakmilikPermohonan.luasPelanB1 - actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama} / <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan }" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama} x 100</div></b>
                                <br/>
                                <b><div align="center"><s:format value="${actionBean.hakmilikPermohonan.luasPelanB1}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama} - <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama} = <s:format value="${actionBean.hakmilikPermohonan.luasPelanB1 - actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama}</div></b>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 eq null}">
                                <b><div style="color:red" align="center">Belum Dikemaskini</div></b>
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.luasPelanB1 ne null}">
                                <b><div align="center">
                                        <%--<s:format formatPattern="#,###,##0.0000" value="${actionBean.totalKira}" /> %--%>
                                        <span id='totalKira'></span>
                                    </div></b>
                                </c:if>
                        </td>
                    </tr>
                </table>
                <p style="color:red" align="center">
                    *Perlu dikemukakan PTD untuk kelulusan tambahan* (Kurang dari 20%)<br/>
                </p>
                <p style="color:red" align="center">
                    *Perlu dikemukakan MMK untuk kelulusan tambahan* (Lebih dari 20%)<br/>
                </p>
                <%--<table border="0" align="center">
                    <tr>
                        <td><b>Luas Pelan B1 :</b>&nbsp;</td>
                        <td><s:format value="${actionBean.hakmilikPermohonan.luasPelanB1}" formatPattern="#,###,##0.0000"/> &nbsp;
                            ${actionBean.hakmilikPermohonan.luasPelanB1Uom.nama}</td>
                    </tr><tr>
                        <td><b>Luas Lulus :</b>&nbsp;</td>
                        <td><s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> &nbsp;
                            ${actionBean.hakmilikPermohonan.luasLulusUom.nama}</td>
                    </tr>
                </table>--%>
            </c:if>    
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">       
                <table class="tablecloth" border="0" align="center">
                    <tr>
                        <td>No PW :</td>
                        <td><s:text name="noPW" size="25" value="${actionBean.noPW}"/></td>
                    </tr>
                </table>           
            </c:if>    
            <p align="center">                
                <c:if test="${actionBean.stageId eq 'g_terima_pw'}">        
                    <s:button name="lakarPelan" id="lakarPelan" value="Terima PW" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;                
                </c:if>   
                <c:if test="${actionBean.stageId eq '22TrmPlnWrt' || actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <s:button name="lakarPelan" id="lakarPelan" value="Terima PW" class="btn"  onclick="RunProgram3('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;                
                </c:if> 
                <c:if test="${actionBean.stageId ne 'g_terima_pw' && actionBean.permohonan.kodUrusan.kod ne 'PHLL' && actionBean.permohonan.kodUrusan.kod ne 'PBMT' && actionBean.permohonan.kodUrusan.kod ne 'PBHL' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'PRIZ'}">
                    <s:button name="lakarPelan" id="lakarPelan" value="Terima PA/B1" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLL' || actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                    <s:button name="lakarPelan" id="lakarPelan" value="Terima PA/B1" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>    
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <c:if test="${actionBean.stageId eq 'g_terima_pa_b1'}">
                        <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">
                            <s:button name="lakarPelan" id="lakarPelan" value="Terima PA/B1" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.permohonan.permohonanSebelum.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.permohonan.permohonanSebelum eq null}">
                            <s:button name="lakarPelan" id="lakarPelan" value="Terima PA/B1" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </c:if>
                        <s:button class="btn" value="Kemaskini" name="pilih" id="pilih" onclick="selectRadioBox();"/>       
                    </c:if>                   
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                    <s:button name="simpanPW" class="btn" value="Simpan" onclick="if(valPW())doSubmit(this.form,this.name,'page_div')"/>
                </c:if>
            </p>        
        </fieldset>
    </div>   
</s:form>
