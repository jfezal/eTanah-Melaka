<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript">

    $(document).ready(function() {    

            $("input:checkbox[title]").tooltip({
                // tweak the position
                //offset: [1, 2],

                // use the "slide" effect
                effect: 'slide'
            }).dynamic({ bottom: { direction: 'down', bounce: true } });

        });        
        
        
        function doPrintViaApplet () {            
            var FILE_TO_PRINT = '';
            var DELIM = ',';

            $('.sign').each(function(index){
                index = index + 1;
                if($('#vdoc' + index).is(':checked')){                    
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#vdoc' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#vdoc' + index).val();
                    }
                }
                if($('#dhke' + index).is(':checked')){
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#dhke' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#dhke' + index).val();
                    }
                }

                if($('#dhde' + index).is(':checked')){
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#dhde' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#dhde' + index).val();
                    }
                }
                 if($('#pelan' + index).is(':checked')){
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#pelan' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#pelan' + index).val();
                    }
                }
                if($('#hakmilikBatal' + index).is(':checked')){
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#hakmilikBatal' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#hakmilikBatal' + index).val();
                    }
                }

            });            
            
            if (FILE_TO_PRINT != '') {
                var a = document.getElementById('applet2');                
                a.doPrint(FILE_TO_PRINT);
                //a.printDocument(FILE_TO_PRINT);
            }            
        }

        function doSignFile(dateSvr, flag) {
        
            //alert("flag:"+flag);
            //var today = new Date();
            //alert(today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getYear());
            var DELIM = "|";
            var parameterToSign = '';
            var v_file = '';
            var dhde_file = '';
            var dhke_file = '';
            var b_file = '';
            var batal_file = '';
        
            $('.sign').each(function(index){
                index = index + 1;
                if($('#vdoc' + index).is(':checked')){
                    v_file = $('#vdoc' + index).val() + '#' + $('#vdoc_path' + index).val() + '#vdoc#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + v_file;
                    } else {
                        parameterToSign = v_file;
                    }
                }
            
                if($('#dhke' + index).is(':checked')){
                    dhke_file = $('#dhke' + index).val() + '#' + $('#dhke_path' + index).val() + '#dhke#' + flag;
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + dhke_file;
                    } else {
                        parameterToSign = dhke_file;
                    }
                }
            
                if($('#dhde' + index).is(':checked')){
                    dhde_file = $('#dhde' + index).val() + '#' + $('#dhde_path' + index).val() + '#dhde#' + flag;
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + dhde_file;
                    } else {
                        parameterToSign = dhde_file;
                    }
                }

                if($('#pelan' + index).is(':checked')){
                    b_file = $('#pelan' + index).val() + '#' + $('#pelan_path' + index).val() + '#b1#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + b_file;
                    } else {
                        parameterToSign = b_file;
                    }
                }

                if($('#pelan_2_' + index).is(':checked')){
                    b_file = $('#pelan_2_' + index).val() + '#' + $('#pelan_path_2_' + index).val() + '#b1#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + b_file;
                    } else {
                        parameterToSign = b_file;
                    }
                }

                if($('#hakmilikBatal' + index).is(':checked')){
                    batal_file = $('#hakmilikBatal' + index).val() + '#' + $('#hakmilikBatal_path' + index).val() + '#dhde#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + batal_file;
                    } else {
                        parameterToSign = batal_file;
                    }
                }
            
            });
            //alert(parameterToSign);
            if (parameterToSign != '') {
                var signer = new ActiveXObject("SAPDFSigner.Form1");
                signer.SigningPDFFile(parameterToSign, dateSvr);
                if(signer.getValue()!=""){
                    document.eload.message.value = signer.getValue();
                }
            } else {
                alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
        }


        function signMultipleFiles(folderId, txtJawatan){
            var DELIM = "|";
            var docToSign = '';
            var folderToSign = '';

            $('.sign').each(function(index){
                index = index + 1;
                if($('#vdoc' + index).is(':checked')){
                    if(docToSign != ''){
                        docToSign = docToSign + DELIM + $('#vdoc' + index).val();
                    } else{
                        docToSign = $('#vdoc' + index).val();
                    }

                    if(folderToSign != '') {
                        //folderToSign = folderToSign + DELIM + folderId;
                        folderToSign = folderToSign + DELIM + $('#vdoc_path' + index).val();
                    } else {
                        folderToSign = $('#vdoc_path' + index).val();
                    }
                } else if ($('#vdoc' + index).val() == 'undefined' ){
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM;
                }
                else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM;
                }

                if($('#dhke' + index).is(':checked')){
                    if(docToSign != ''){
                        docToSign = docToSign + DELIM + $('#dhke' + index).val();
                    } else{
                        docToSign = $('#dhke' + index).val();
                    }

                    if(folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#dhke_path' + index).val();
                    } else {
                        folderToSign = $('#dhke_path' + index).val();;
                    }
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }

                if($('#dhde' + index).is(':checked')){
                    if(docToSign != ''){
                        docToSign = docToSign + DELIM + $('#dhde' + index).val();
                    } else{
                        docToSign = $('#dhde' + index).val();
                    }

                    if(folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#dhde_path' + index).val();
                    } else {
                        folderToSign = $('#dhde_path' + index).val();;
                    }
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }

                if($('#pelan' + index).is(':checked')){
                    if(docToSign != ''){
                        docToSign = docToSign + DELIM + $('#pelan' + index).val();
                    } else{
                        docToSign = $('#pelan' + index).val();
                    }

                    if(folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#pelan_path' + index).val();
                    } else {
                        folderToSign =  $('#pelan_path' + index).val();;
                    }
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }
            });

            //alert(docToSign);
            //alert(folderToSign);
            if (docToSign != '||||' && folderToSign != '||||') {
                var signer = new ActiveXObject("SAPDFSigner.Form1");
                signer.SigningPDFFile(docToSign, folderToSign, txtJawatan);
                if(signer.getValue()!=""){
                    document.eload.message.value = signer.getValue();
                }
            } else {
                alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
        
        }

        function signFilesVDoc(fileNme,fldrNme,txtJawatan,template,signature, signer){

            //var signer = new ActiveXObject("SAPDFSigner.Form1");
            //signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);
            signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 340, 590, 430, 620, template,1, signature)
            if(signer.getValue()!=""){
                document.eload.message.value = signer.getValue();
            }

            if(document.eload.message.value!="undefined"){
                var urls = '${pageContext.request.contextPath}/dokumen/view/' + fileNme + '?signForm&afterSign=true&vdoc=true';
                document.eload.action = urls;
                document.eload.submit();
            }
        }

        function signFilesB1(fileNme,fldrNme,txtJawatan,template,signature){

            var signer = new ActiveXObject("SAPDFSigner.Form1");

            //signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);
            signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 420, 80, 540, 140, template,1, signature)
            if(signer.getValue()!=""){

                document.eload.message.value = signer.getValue();

            }

            if(document.eload.message.value!="undefined"){
                var urls = '${pageContext.request.contextPath}/dokumen/view/' + fileNme + '?signForm&afterSign=true';
                document.eload.action = urls;
                document.eload.submit();
            }
        }


        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doViewGeranSemasa(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?watermark=true';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function selectAll (a) {
            var id = a.id;
            var len = $('.sign').length;
            //alert(len);
            //alert(id);
            for (i =1; i<=len; i++) {
                if (id == 'semuaVdoc') {
                    var c = document.getElementById("vdoc" + i);
                    c.checked = a.checked;
                } else if (id == 'semuaDHDe'){
                    var c = document.getElementById("dhde" + i);
                    c.checked = a.checked;
                } else if (id == 'semuaDHKe'){
                    var c = document.getElementById("dhke" + i);
                    c.checked = a.checked;
                } else if (id == 'semuaPelan'){
                    var c = document.getElementById("pelan" + i);
                    c.checked = a.checked;
                } else if (id == 'semuaHakmilikBatal') {
                    var c = document.getElementById("hakmilikBatal" + i);
                    c.checked = a.checked;
                } else if (id == 'semuaPelan2'){
                    var c = document.getElementById("pelan_2_" + i);
                    c.checked = a.checked;
                }
            }
        }

</script>
<span class='instr'>Sila semak terlebih dahulu sebelum tandatangan. Sila klik pada kotak untuk pilih dokumen untuk ditandatangani.[NOTA]</span><br/><br/>
<div class="subtitle displaytag" align="center">   
    <s:form beanclass="etanah.view.dokumen.FolderAction" name="eload">
        <s:messages/>
        <s:errors/>
        <s:hidden name="permohonan.idPermohonan"/>
        <%-- <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">--%>        

        <display:table style="width:50%;" class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
            <display:column title="No" >
                <c:out value="${line_rowNum}"/>
            </display:column>
            <c:choose>
                <c:when test="${line.hakmilik.idHakmilikInduk != null}">
                    <display:column property="hakmilik.idHakmilikInduk" title="Hakmilik" class="sign"/>
                </c:when>
                <c:otherwise>
                    <display:column property="hakmilik.idHakmilik" title="Hakmilik" class="sign"/>
                </c:otherwise>
            </c:choose>
            <display:column title="Geran Semasa">
                <c:if test="${!empty line.hakmilik.dhde.idDokumen}">
                    <a href="#" id="p" onclick="doViewReport('${line.hakmilik.dhde.idDokumen}');return false;">Papar</a>
                </c:if>
                <c:if test="${empty line.hakmilik.dhde.idDokumen}">
                    -
                </c:if>
            </display:column>

            <display:column title="VDOC<br/> <input type='checkbox' id='semuaVdoc' name='semua' onclick='javascript:selectAll(this);' checked='checked' />"
                            style="width:20%;">
                <c:if test="${line.dokumen1.namaFizikal != null}">
                    <a href="#" id="p" onclick="doViewReport('${line.dokumen1.idDokumen}');return false;">Papar</a>
                    <c:if test="${line.dokumen1.kodDokumen.kod eq 'VDOC'}">                        
                        <c:if test="${line_rowNum eq 1}">                            
                            <input type="checkbox" id="vdoc${line_rowNum}"
                                   title="Sila klik untuk tandatangan ${line.dokumen1.kodDokumen.nama}"
                                   value="${line.dokumen1.idDokumen}"
                                   checked='checked'/>
                        </c:if>                        
                        <c:set var="path"/>
                        <c:forTokens delims="/" items="${line.dokumen1.namaFizikal}" var="items" begin="0" end="3">
                            <c:set var="path" value="${path}/${items}"/>
                        </c:forTokens>
                        <input type="hidden" id="vdoc_path${line_rowNum}" value="${path}"/>
                    </c:if>                    
                </c:if>
            </display:column>

                        <%--TODO:Filter by kod_urusan--%>
                        <c:if test="${actionBean.groupFlag eq '1'
                                              or actionBean.groupFlag eq '2'}">
                                <display:column title="DHDe <br/> <input type='checkbox' id='semuaDHDe' name='semua' onclick='javascript:selectAll(this);' checked='checked' />" style="width:20%">
                <c:if test="${line.dokumen3.namaFizikal != null}">
                    <a href="#" id="p" onclick="doViewReport('${line.dokumen3.idDokumen}');return false;">Papar</a>
                    <input type="checkbox" id="dhde${line_rowNum}"
                           title="Sila klik untuk tandatangan ${line.dokumen3.kodDokumen.nama}"
                           value="${line.dokumen3.idDokumen}"
                           checked='checked'/>
                    <c:set var="path"/>
                    <c:forTokens delims="/" items="${line.dokumen3.namaFizikal}" var="items" begin="0" end="3">
                        <c:set var="path" value="${path}/${items}"/>
                    </c:forTokens>
                    <input type="hidden" id="dhde_path${line_rowNum}" value="${path}"/>
                </c:if>
            </display:column>
        
       <c:if test="${actionBean.groupFlag eq '2'}">
               <display:column title="DHKe <br/> <input type='checkbox' id='semuaDHKe' name='semua' onclick='javascript:selectAll(this);' checked='checked'/>"
                            style="width:20%">
                <c:if test="${line.dokumen2.namaFizikal != null}">
                    <a href="#" id="p" onclick="doViewReport('${line.dokumen2.idDokumen}');return false;">Papar</a>
                    <input type="checkbox" id="dhke${line_rowNum}"
                           title="Sila klik untuk tandatangan ${line.dokumen2.kodDokumen.nama}"
                           value="${line.dokumen2.idDokumen}"
                           checked='checked'/>
                    <c:set var="path"/>
                    <c:forTokens delims="/" items="${line.dokumen2.namaFizikal}" var="items" begin="0" end="3">
                        <c:set var="path" value="${path}/${items}"/>
                    </c:forTokens>
                    <input type="hidden" id="dhke_path${line_rowNum}" value="${path}"/>
                </c:if>
            </display:column>
       </c:if>
</c:if>
                    <%--end--%>
        <!--N9 Only-->
        <c:if test="${cetak}">
            <display:column title="Surat Daftar/ Tolak / Gantung" style="width:20%">
                <c:if test="${line.dokumen4.namaFizikal != null}">
                    <a href="#" id="p" onclick="doViewReport('${line.dokumen4.idDokumen}');return false;">Papar</a>                   
                </c:if>
            </display:column>
        </c:if>
 
        </display:table>
        <br/>
        <c:if test="${fn:length(actionBean.senaraiHakmilikBatal) > 0}">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Yang Akan<em>DIBATALKAN</em></legend><br/>
                <display:table style="width:50%;" class="tablecloth" name="${actionBean.senaraiHakmilikBatalUnique}" cellpadding="0" cellspacing="0" id="line2">
                    <display:column title="No" >
                        <c:out value="${line2_rowNum}"/>
                    </display:column>
                    <display:column property="hakmilikSejarah.idHakmilik" title="Hakmilik" class="sign"/>
                    <display:column title="DHDe <br/><input type='checkbox' id='semuaHakmilikBatal' name='semua' onclick='javascript:selectAll(this);' checked='checked'/>">
                        <c:if test="${line2.hakmilik.dhde != null}">
                            <a href="#" id="p" onclick="doViewReport('${line2.hakmilik.dhde.idDokumen}');return false;">Papar</a>
                            <input type="checkbox" id="hakmilikBatal${line2_rowNum}"
                                   title="Sila klik untuk tandatangan ${line2.hakmilik.dhde.kodDokumen.nama}"
                                   value="${line2.hakmilik.dhde.idDokumen}"
                                   checked='checked'/>

                            <c:set var="path"/>
                            <c:forTokens delims="/" items="${line2.hakmilik.dhde.namaFizikal}" var="items" begin="0" end="3">
                                <c:set var="path" value="${path}/${items}"/>
                            </c:forTokens>
                            <input type="hidden" id="hakmilikBatal_path${line2_rowNum}" value="${path}"/>
                        </c:if>
                    </display:column>
                </display:table>
                <br/>
            </fieldset>  
        </c:if>

        <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0 || fn:length(actionBean.senaraiHakmilikBatal) > 0}">

            <c:set var="prm" value="N"/>
            <c:if test="${!empty multiple_sign_kekal}">
                <c:set var="prm" value="S"/>
            </c:if>
            <c:if test="${!empty multiple_sign_sementara}">
                <c:set var="prm" value="Y"/>
            </c:if>

            <br/>
            <%--<s:button name="" id="" value="T/tangan" class="btn"
                      onclick="signMultipleFiles('${idFolder}', '${actionBean.pengguna.jawatan}');"/>--%>
            <s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${today}', '${prm}');"/>
            <c:if test="${cetak}">
                <s:button name="" value="Cetak" class="btn" onclick="doPrintViaApplet();"/>
            </c:if>            
        </c:if>
        <%-- </c:if>--%>
    </s:form>

    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
        ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
        ${pageContext.request.contextPath}/commons-logging.jar,
        ${pageContext.request.contextPath}/swingx-1.6.jar,
        ${pageContext.request.contextPath}/log4j-1.2.12.jar,
        ${pageContext.request.contextPath}/jpedal_trial.jar"
            codebase = "."
            name     = "applet2"
            id       = "applet2"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
         <param name ="disabledWatermark" value="true"/>
        <param name ="method" value="pdfp">
         <%
        Cookie[] cookies = request.getCookies();
        StringBuffer sb = new StringBuffer();
        for (int i =0; i < cookies.length; i++) {
           sb.setLength(0);
           sb.append(cookies[i].getName());
           sb.append("=");
           sb.append(cookies[i].getValue());
          %>
          <param name="Cookie<%= i %>" value="<%= sb.toString() %>"><%
        }
        %>
</applet>

</div>

