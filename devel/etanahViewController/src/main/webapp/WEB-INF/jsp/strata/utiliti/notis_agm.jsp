<%-- 
    Document   : notis_agm
    Created on : Feb 11, 2014, 10:56:37 AM
    Author     : azri
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Notis 1st AGM</title>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
            
        $(".datepicker1").datepicker({changeYear: true});
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}); 
        $('#carian2').hide(); 
              
        $('#seterusnya').click(function() {
            doBlockUI();
        });
    });

    function validateHakmilik(idxHakmilik){
        var val = $("#hakmilik" + idxHakmilik).val();
        frm = this.form;
        if (val == null || val == "") return;
        val = val.toUpperCase();
        $("#hakmilik" + idxHakmilik).val(val);

      

        $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                $("#msg" + idxHakmilik).html("OK");
            } else if(data =='0'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
            } else if(data =='2'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
            } else{
                alert(data);
            }
        });
    }
        
    function changeCarian1(){
        var cari=document.getElementById("idcarian1").value;
        if(cari == 'byTarikh')
        {
            $('#carian1').show();  
            $('#carian2').hide(); 
            $('#idHakmilik').val("");                                   
        } 
    }
    
    function changeCarian2(){
        var cari=document.getElementById("idcarian2").value;
        if(cari == 'byHakmilik')
        {          
            $('#carian1').hide();  
            $('#carian2').show(); 
            $('#trhMula').val("");
            $('#trhTamat').val("");
         
        }
    }
    
    // function filterKodBPM(f){
    //                var kodDaerah = f
    //                //alert(kodDaerah);
    //                //var q = $(f).formSerialize();
    //                $.post('${pageContext.request.contextPath}/strata/utiliti/notis_1st_AGM?senaraiKodBPMByDaerah&kodDaerah='+kodDaerah,
    //                function(data){
    //                    if(data != ''){
    //                        $('#partialKodBPM').html(data);
    //                    }
    //                }, 'html');
    //            }


    function clearForm() {
        $('#trhMula').val("");
        $('#trhTamat').val("");
        $('#idHakmilik').val("");
    }

    function janaNotis(val) {
        //    JANA DOKUMEN
        doBlockUI();
        var url = '${pageContext.request.contextPath}/strata/utiliti/notis_1st_AGM?janaDokumen&id=' + val;
        frm = document.form1;
        frm.action = url;
        frm.submit();
    }

    function doPrintViaApplet(id) {
        //    alert(id);
        var a = document.getElementById('applet');
        a.doPrint(id.toString());
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
        
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890GgRrNnMmPpHhSsDd";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
        
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
    }
    function filterBPM(kodBPM, frm) {
        var daerah = $('#daerah').val();
        var url = '${pageContext.request.contextPath}/strata/utiliti/notis_1st_AGM?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
        frm.action = url;
        frm.submit();
    }
    function filterDaerah(kodDaerah, frm) {
        //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
        var url = '${pageContext.request.contextPath}/strata/utiliti/notis_1st_AGM?penyukatanBPM&daerah=' + kodDaerah;
        frm.action = url;
        frm.submit();

    }
</script>
<div class="senaraiTukarganti">
    <%--<s:messages />--%>
    <s:errors />
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.strata.utiliti.NotisAGMActionBean" name="form1">
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/> 
        <fieldset class="aras1">
            <legend>Notis 1st AGM </legend>    
            <br/>

            <!--            <p>
                            <label>Carian : </label>             
                            <input type="radio" name="carian" checked="checked" value="byTarikh" id="idcarian1" onclick ="changeCarian1()"/> Tarikh
                            &nbsp; &nbsp;<input type="radio" name="carian" value="byHakmilik" id="idcarian2" onclick ="changeCarian2()"/> Hakmilik
            
                        </p>-->
            <br/>
            <!--<div id="carian1" class="subtitle">-->
            <p>
                <label>Tarikh Mula : </label>
                <s:text name="trhMula" class="datepicker" id="trhMula" formatPattern="dd/MM/yyyy" formatType="date" size="20"/>
            </p>
            <p>
                <label>Tarikh Tamat : </label>
                <s:text name="trhTamat" class="datepicker" id="trhTamat" formatPattern="dd/MM/yyyy" formatType="date" size="20"/>
            </p>

            <!--            </div>
                        <div id="carian2" class="subtitle">-->

            <p>
                <label style="color: red"> ( atau ) </label>      
            </p>

            <br/>

            <label>  ID Hakmilik :</label>
            <s:text class="hakmilik" name="idHakmilik" id="idHakmilik" onkeyup="validateNumber(this,this.value);"/>&nbsp;



            <!--</div>-->

            <br/><br/>
            <div id="clickbutton">
                <p align="center">
                    <s:submit name="seterusnya" value="Cari" class="btn" id="seterusnya" />&nbsp; 
                    <s:button name="reset" value="Isi Semula" class="btn" id="reset" onclick="clearForm();"/>
                </p>
            </div>
            <br>
        </fieldset>

        <c:if test="${fn:length(actionBean.listNotisAGM) > 0}">
            <fieldset class="aras1">
                <legend>Senarai Notis 1st AGM</legend>  
                <br>

                <div align="center">
                    <display:table class="tablecloth" style="width:95%;" id="line" cellpadding="0" cellspacing="0" pagesize="20"
                                   requestURI="/strata/utiliti/notis_1st_AGM" name="${actionBean.listNotisAGM}" >
                        <display:column title="Bil" style="width:1%;"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Id Hakmilik Induk" ><div align="center">${line.idHakmilikInduk}</div></display:column>
                        <display:column title="Peratus" ><div align="center">${line.percent} % </div></display:column>            
                        <display:column title="Jana Notis AGM" style="width:15%;">
                            <div align="center">
                                <%--<c:if test="${fn:length(line.idDok) == 0}">--%>
                                <s:button name="" value="Jana Notis" class="btn" onclick="janaNotis('${line.idHakmilikInduk}');"/>                           
                                <%--</c:if>--%>
                                <%--<c:if test="${fn:length(line.idDok) >= 1}">--%>
                                <%--<s:button name="" value="Jana Notis" class="btn" disabled="true" onclick="janaNotis('${line.idHakmilikInduk}');"/>--%>
                                <%--</c:if>--%>
                            </div>              
                        </display:column>

                        <display:column title="Cetak" style="width:7%;">
                            <c:if test="${fn:length(line.idDok) > 1}">
                                <div align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line.tajukDok}"
                                         onclick="doPrintViaApplet('${line.idDok}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/>
                                </div>    
                            </c:if>
                        </display:column>
                        <display:column title="Papar" style="width:7%;">
                            <c:if test="${fn:length(line.idDok) > 1}">
                                <div align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" width="20" height="20" title="Papar ${line.tajukDok}"
                                         onclick="doViewReport('${line.idDok}');" onmouseover="this.style.cursor = 'pointer';"/>
                                </div>    
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
                <br>
            </fieldset>   
            <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                    ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                    ${pageContext.request.contextPath}/commons-logging.jar,
                    ${pageContext.request.contextPath}/swingx-1.6.jar,
                    ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                    ${pageContext.request.contextPath}/jpedal_demo.jar"
                    codebase = "."
                    name     = "applet"
                    id       = "applet"
                    width    = "1"
                    height   = "1"
                    align    = "middle">
                <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
                <param name ="kodNegeri" value="05">
                <param name ="method" value="pdfp">
                <!--      <param name ="disabledWatermark" value="true"/>--> 
                <param name ="idPengguna" value="${idPengguna}"/>
                <%
                    Cookie[] cookies = request.getCookies();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < cookies.length; i++) {
                        sb.setLength(0);
                        sb.append(cookies[i].getName());
                        sb.append("=");
                        sb.append(cookies[i].getValue());
                %>
                <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                    }
                %>
            </applet>    
        </c:if>
        <br>
    </s:form>
</div>