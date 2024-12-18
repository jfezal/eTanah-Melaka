<%-- 
    Document   : gisStrata
    Author     : Mohd Syafiq
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<script type="text/javascript">
    $(document).ready(function() {
    <c:if test="${!actionBean.selesaiBtn}">
            $('#hantar').attr("disabled","true");
    </c:if>
    <c:if test="${actionBean.selesaiBtn}">
            $('#hantar').removeAttr("disabled");
            $('#hantar').removeClass('disablebtn');
            $('#hantar').addClass('btn');
    </c:if> 
            var hiddenDiv = document.getElementById("hiddenDiv");
            var buttons = document.getElementById("buttons");
            var hiddenHantar = document.getElementById("hiddenHantar").value;
            if(hiddenHantar == '1'){
    <c:if test='${actionBean.dwnldDisabled && !actionBean.showKomms && actionBean.checked}'>
                buttons.innerHTML = "<input type='button' disabled='disabled' id='muatTurun' onclick='muatturun(\"${actionBean.stageId}\",\"${actionBean.fileToDownload}\")' name='muatTurun' value='Muat Turun' class='btn'/> <input type='button' id='batal' onclick='tutuphantar()' name='tutup' value='Batal' class='btn'/>";
    </c:if>
    <c:if test='${!actionBean.dwnldDisabled && !actionBean.showKomms && actionBean.checked}'>
                buttons.innerHTML = "<input type='button' id='muatTurun' onclick='muatturun(\"${actionBean.stageId}\",\"${actionBean.fileToDownload}\")' name='muatTurun' value='Muat Turun' class='btn'/> <input type='button' id='batal' onclick='tutuphantar()' name='tutup' value='Batal' class='btn'/>";
    </c:if>
                hiddenDiv.style.visibility  = "visible";
            }else if(hiddenHantar == '2'){
                hiddenDiv.style.visibility  = "visible";
            }else{
                hiddenDiv.style.visibility  = "hidden";
            }
    <c:if test="${!actionBean.enabledHantar}">
            $("[name=hantar]").each(function (i) {
                $(this).click(function () {
                    var selection = $(this).val();
                    if (selection == '1') {
                        $('#muatTurun2').removeAttr("disabled");
                    }else{
                        $('#muatTurun2').removeAttr("disabled");
                    }             
                });
            });
    </c:if>
             
             
            //
    <c:if test='${!actionBean.checked}'>   
        <c:forEach items="${actionBean.listDok}" var="doks" varStatus="statusC">
                var docs = '${doks.namaFizikal}';
                var kodDok = '${doks.noRujukan}';
                if(docs != null && docs != "" && kodDok == '2'){
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = true;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "2";
                }else if(docs != null && docs != "" && kodDok != '2'){
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = false;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "1";
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').setAttribute("onclick", "verify('${doks.kodDokumen.kod}')");
                }else{
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = false;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "1";
                }
                $('#chkbox${doks.kodDokumen.kod}').change(function() {
                    $("#hidval${doks.kodDokumen.kod}").val(($(this).is(':checked')) ? "2" : "1");
                });
        </c:forEach>
    </c:if>
    <c:if test='${actionBean.checked}'>   
        <c:forEach items="${actionBean.listDok}" var="doks" varStatus="statusD">
                var checked = '${doks.catatanMinit}';
                var docs = '${doks.namaFizikal}';
                if((docs != null && docs != "") && checked == '2'){
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = true;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "2";
                }else if ((docs != null && docs != "") && checked == '1'){
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = false;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "1";
                }else if ((docs == null && docs == "") && checked == '1'){
                    document.getElementById("chkbox"+ '${doks.kodDokumen.kod}').checked = false;
                    document.getElementById("hidval"+ '${doks.kodDokumen.kod}').value = "1";
                }
                $('#chkbox${doks.kodDokumen.kod}').change(function() {
                    $("#hidval${doks.kodDokumen.kod}").val(($(this).is(':checked')) ? "2" : "1");
                });
        </c:forEach>
    </c:if>
        });
        function muatturun2()
        {
            var hantar = $('input[name=hantar]:radio:checked').val();
            if(hantar == '1') {
                var stageId = document.getElementById("stageId").value;
                var fileToDownload = document.getElementById("fileToDownload").value;
                var r=confirm("Kaedah yang dipilih adalah 'JUPEM2U'. Anda pasti?");
                if (r==true)
                {
                    var val = document.getElementById("hiddenHantar").value;
                    var dokVal = document.getElementById("form1");
                    var q = $(dokVal).formSerialize();
                    var url = '${pageContext.request.contextPath}/strata/gis?updateVal&hantarfile='+val;
                    $.get(url, q,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                    location.href="DownloadServlet?stageId="+stageId+"&filename="+fileToDownload;
                    $('#hantar').removeAttr("disabled");
                    $('#hantar').removeClass('disablebtn');
                    $('#hantar').addClass('btn');
                    window.open('http://jupem2u.jukd.gov.my/j2u/jupem2u.aspx', 'JUPEM2U', 'height=' + screen.height + ',width=' + screen.width + ',resizable=yes,scrollbars=yes,toolbar=yes,menubar=yes,location=yes', '_blank');
                }
            }else if(hantar == '2'){
                var r=confirm("Kaedah yang dipilih adalah 'KOMMS Server'. Anda pasti?");
                if (r==true)
                {
                    var val = document.getElementById("hiddenHantar").value;
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });
                    var dokVal = document.getElementById("form1");
                    var q = $(dokVal).formSerialize();
                    var url = '${pageContext.request.contextPath}/strata/gis?hantarKeKomms&hantarfile='+val;
                    $.get(url, q,
                    function(data){
                        $('#page_div').html(data);
                        $.unblockUI();
                    },'html');
                }
            }
        }
        function simpandoc(){
            // var hiddenDiv = document.getElementById("hiddenDiv");
            // document.getElementById('hiddenHantar').value = selection;
            var val = document.getElementById("hiddenHantar").value;
            //hiddenDiv.style.visibility  = "hidden";
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var dokVal = document.getElementById("form1");
            var q = $(dokVal).formSerialize();
            var url = '${pageContext.request.contextPath}/strata/gis?simpanDoc';
            $.get(url, q,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');

        }
        function tutuphantar(){
            var val = document.getElementById("hiddenHantar").value;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/strata/gis?tutupHantar&hantarfile='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');

        }
        function doViewReport(d) {
            window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
        }
        
        function noFile(kod){
            alert("Sila muat naik fail di tab 'Dokumen'");
            document.getElementById("chkbox"+ kod).checked = false;
            document.getElementById("hidval"+ kod).value = "1";
        }
        function verify(kod){
            if(document.getElementById("chkbox"+ kod).checked){     
                var r=confirm("Dokumen yang dipilih bukan dari senarai fail yang wajib dihantar. Anda pasti untuk memilih dokumen ini?");
                if (r==true)
                {
                    document.getElementById("chkbox"+ kod).checked = true;
                    document.getElementById("hidval"+ kod).value = "2";
                }
                else
                {
                    document.getElementById("chkbox"+ kod).checked = false;
                    document.getElementById("hidval"+ kod).value = "1";
                }
            }
            
        }
        

</script>
<style>
    #simpan{
        padding-left: 10px;
        padding-top: 20px;
    }
    #kaedah{
        padding-left: 155px;
    }
</style>
<%--Original <s:form name="form1" beanclass="etanah.view.strata.GisStrataActionBean">--%>
<s:form id="form1" name="form1" beanclass="etanah.view.strata.GisStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <s:hidden id="hiddenHantar" name="hiddenHantar"/>
            <legend>${actionBean.gisStage}</legend>
            <p><font color="Green">Arahan: Sila pilih fail untuk dihantar </font></p>
            <center>
                <display:table name="${actionBean.listDok}" class="tablecloth" id="dok" style="width:100%">

                    <display:column title="Pilih">
                        <center>
                            <c:if test="${!actionBean.dwnldDisabled && !actionBean.showKomms && !actionBean.checked}">
                                <c:if test="${dok.namaFizikal != null}">
                                    <s:checkbox name="${dok.kodDokumen.kod}" id="chkbox${dok.kodDokumen.kod}" value="${dok.idDokumen}"/>
                                </c:if>
                                <c:if test="${dok.namaFizikal == null}">
                                    <s:checkbox name="${dok.kodDokumen.kod}" id="chkbox${dok.kodDokumen.kod}" value="${dok.idDokumen}" onclick="noFile('${dok.kodDokumen.kod}')"/>
                                </c:if>
                            </c:if>
                            <c:if test="${(actionBean.dwnldDisabled && !actionBean.showKomms && actionBean.checked) || (!actionBean.dwnldDisabled && actionBean.showKomms && actionBean.checked) || (!actionBean.dwnldDisabled && !actionBean.showKomms && actionBean.checked)}">
                                <c:if test="${dok.namaFizikal != null}">
                                    <s:checkbox name="${dok.kodDokumen.kod}" id="chkbox${dok.kodDokumen.kod}" value="${dok.idDokumen}" disabled="disabled"/>
                                    <c:if test="${dok.catatanMinit == 2}">
                                        <s:hidden name="${dok.kodDokumen.kod}" id="${dok.kodDokumen.kod}" value="${dok.idDokumen}" />
                                    </c:if>
                                </c:if>
                                <c:if test="${dok.namaFizikal == null}">
                                    <s:checkbox name="${dok.kodDokumen.kod}" id="chkbox${dok.kodDokumen.kod}" value="${dok.idDokumen}" disabled="disabled"/>
                                </c:if>
                            </c:if>
                            <s:hidden name="hidval${dok.kodDokumen.kod}" id="hidval${dok.kodDokumen.kod}"/></center>
                        </display:column>
                        <display:column title="Kod Dokumen"><center>${dok.kodDokumen.kod}</center></display:column>
                    <display:column title="Nama Dokumen">${dok.kodDokumen.nama}</display:column>
                    <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${dok.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${dok.idDokumen}');return false;" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" />
                            </c:if>
                        </p>
                    </display:column>
                </display:table>
            </center>
            <div id="simpan">
                <s:button id="simpanDoc" onclick="simpandoc()"  name="simpanDoc" value="Simpan" class="btn"/> 
                <c:if test="${actionBean.attachments || actionBean.strataXML}">
                    <s:button id="batal" onclick="tutuphantar()" name="tutup" value="Batal" class="btn"/>
                </c:if>
            </div>
            <div id="buttons">
            </div>
            <br/>
            <div id="hiddenDiv">
                <c:if test="${actionBean.showKomms}">
                    <br/>
                    <% int i = 1;%>
                    <label>Senarai Dokumen yang akan dihantar :</label>
                    <center>
                        <display:table name="${actionBean.dokumen}" class="tablecloth" id="dok" style="width:75%">

                            <display:column title="Kod Dokumen"><center>${dok.kodDokumen.kod}</center></display:column>
                            <display:column title="Nama Dokumen">${dok.kodDokumen.nama}</display:column>
                        </display:table>
                    </center></p>
                    <br/>
                    <div id="kaedah">
                        <p><font color="Blue">Sila Pilih Kaedah Penghantaran :</font></p>
                        <p><s:radio name="hantar" id="jupem2u" value="1"> </s:radio>&nbsp; JUPEM2U
                            </p>
                            <p><s:radio name="hantar" id="komms" value="2"> </s:radio>&nbsp; KOMMS SERVER</p>
                            <br/>
                        </div>
                    <c:if test="${actionBean.attachments || actionBean.strataXML}">
                        <br/>
                        <center>
                            <s:hidden name="stageId" id="stageId" value="${actionBean.stageId}"/>
                            <s:hidden name="fileToDownload" id="fileToDownload" value="${actionBean.fileToDownload}"/>
                            <c:if test="${!actionBean.enabledHantar}">
                                <s:button id="muatTurun2" onclick="muatturun2()" name="muatTurun" value="Hantar Fail ke JUPEM" class="longbtn" disabled="disabled"/> 
                            </c:if>
                            <c:if test="${actionBean.enabledHantar}">
                                <s:button id="muatTurun2" onclick="muatturun2()" name="muatTurun" value="Hantar Fail ke JUPEM" class="longbtn"/> 
                            </c:if>
                        </center>
                    </c:if>
                    <c:if test="${!actionBean.attachments && !actionBean.strataXML}">
                        <br/>
                        <p>
                        <center><font color="Red">Tiada fail untuk dihantar!</font></center>
                        <p/>
                        <br/>
                        <p>
                        <center><s:button id="batal" onclick="tutuphantar()" name="tutup" value="Batal" class="btn"/></center>
                        </p>
                    </c:if>
                </c:if>
            </div>
        </fieldset>
    </div>

</s:form>