<%-- 
    Document   : utiliti_report_param_ns
    Created on : May 22, 2013, 10:46:38 PM
    Author     : zabedah.zainal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    var date = new Date();
    var kodCaw = '${actionBean.cawKod}';
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#caw").val(kodCaw);
    });

    function doSubmitOriginal(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == 'DISLTSITE_NS.rdf'
              
    ) && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_mula').val()== ""){
            alert("Sila masukkan Tarikh Mula terlebih dahulu.");
            $('#trh_mula').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_akhir').val()== ""){
            alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
            $('#trh_akhir').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }

        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#caw').val()== ""){
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
            
    }

    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
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

    function doSubmit1(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((report == 'DISLTSITE_NS.rdf') && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
            return false;
        }

        if(report == 'ENFSTMAJU_MLK.rdf' || report == 'DISLaporanMohonJMRE_NS.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }

            if($('#caw').val()== ""){
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                return false;
            }

        }

    <%--utiliti report--%>
            if(report == 'UTL_MMMCL_NS.rdf' || report=='UTL_RekodPermitKerajaan_NS.rdf' || report=='UTL_RekodPermitTMilik_NS.rdf' || report=='UTL_PRIZ_NS.rdf'|| report== 'UTL_RekodPajakLombong_NS.rdf' || report=='UTL_RekodBRPajakLombong_NS.rdf' || report=='UTL_MMMCL_ML.rdf' || report=='UTL_RekodPermitKM_ML.rdf' || report=='UTL_RekodPermitPTG_ML.rdf' || report=='UTL_PRIZ_ML.rdf' || report=='UTL_RekodPermitCG_ML.rdf' || report=='UTL_RekodBRPermitCG_ML.rdf' || report=='UTL_RekodPajakLombong_ML.rdf' || report=='UTL_RekodBRPajakLombong_ML.rdf' || report=='UTL_LURUSAN_NS.rdf' || report=='REP_PERMOHONAN.rdf'){
   
            }

            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }

        function doSubmit(f,val){
            var form = $(f).formSerialize();
            var report = null;
            var report2 = null;
            var negeri = '${actionBean.kodNegeri}';
            var param1 = $('#report_p_kod_caw').val();
            var paramBPM = $('#mukim').val();
            var param2 = "";

            var count = document.getElementById("recordCount").value;
            var count = document.getElementById("recordCount").value;
    <%--alert("count->"+count);--%>
            var pdf1 = false;
            var pdf2 = false;
            for (i = 0; i < count; i++) {
                var urusan = document.getElementById("report_p_kod_urusan" +(i)).value;
                if(urusan=='RAYL'||urusan=='RYKN'||urusan=='RLPTG'||urusan=='PLPTD'||urusan=='PRMMK'||urusan=='PJTK'||urusan=='WMRE'||urusan=='PTBTC'||urusan=='PTBTS'||urusan=='PTMTA'||urusan=='MMRE'||urusan=='BMRE'||urusan=='JMRE'){
                    pdf2 = true;
                }else{
                    pdf1 = true;
                }
                if(param2!=""){                    
                    param2 = param2+"," + document.getElementById("report_p_kod_urusan" +(i)).value;
                }
                
                else
                    param2 = document.getElementById("report_p_kod_urusan" +(i)).value;
            }
            if(negeri == 'melaka'){
                if (val =='1')
                    report = 'UTL_LURUSAN_NS.rdf';
                else if(val =='2'){
                    if(pdf1==true)
                        report = 'REP_PERMOHONAN.rdf';
                    if(pdf2==true)
                        report2 = 'REP_PERMOHONAN_CONTINUE.rdf';
                }
                else if(val =='3')
                    report = 'UTL_LKEMAJUAN_DIS.rdf';
                else if(val =='4')
                    report = 'UTL_LPREMIUM_DIS.rdf';
                   
            }else{
                if(val=='1')
                    report = 'UTL_LURUSAN_NS.rdf';
                else if(val =='2'){
                     if(pdf1==true)
                        report = 'REP_PERMOHONAN.rdf';
                    if(pdf2==true)
                        report2 = 'REP_PERMOHONAN_CONTINUE.rdf';
                }
                else if(val =='3')
                    report = 'UTL_LKEMAJUAN_DIS.rdf';
                else if(val =='4')
                    report = 'UTL_LPREMIUM_DIS.rdf';
                    
            }
    <%--alert(param2);--%>
            if(val=='1'){
                var param3 = $('#report_p_date1').val();
                var param4 = $('#report_p_date2').val();
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?&reportName="+report+"&report_p_kod_caw="+param1+"&report_p_mukim="+paramBPM+"&report_p_kod_urusan="+param2+"&report_p_date1="+param3+"&report_p_date2="+param4, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }else if(val =='2'){
                var param3 = $('#report_p_date1').val();
                var param4 = $('#report_p_date2').val();
                if(pdf1==true){
                    window.open("${pageContext.request.contextPath}/reportGeneratorServlet?&reportName="+report+"&report_p_kod_caw="+param1+"&report_p_mukim="+paramBPM+"&report_p_kod_urusan="+param2+"&report_p_date1="+param3+"&report_p_date2="+param4, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
                if(pdf2==true){
                    window.open("${pageContext.request.contextPath}/reportGeneratorServlet?&reportName="+report2+"&report_p_kod_caw="+param1+"&report_p_mukim="+paramBPM+"&report_p_kod_urusan="+param2+"&report_p_date1="+param3+"&report_p_date2="+param4, "eTanah2",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
            }else if(val =='3'){
                var param3 = $('#report_p_date1').val();
                var param4 = $('#report_p_date2').val();
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?&reportName="+report+"&report_p_kod_caw="+param1+"&report_p_mukim="+paramBPM+"&report_p_kod_urusan="+param2+"&report_p_date1="+param3+"&report_p_date2="+param4, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }else if(val =='4'){
                var param3 = $('#report_p_date1').val();
                var param4 = $('#report_p_date2').val();
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?&reportName="+report+"&report_p_kod_caw="+param1+"&report_p_mukim="+paramBPM+"&report_p_kod_urusan="+param2+"&report_p_date1="+param3+"&report_p_date2="+param4, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }
           

   

    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
        }

        function moveOptions(theSelFrom, theSelTo)
        {

            var selLength = theSelFrom.length;
            var selectedText = new Array();
            var selectedValues = new Array();
            var selectedCount = 0;

            var i;

            for(i=selLength-1; i>=0; i--)
            {

                if(theSelFrom.options[i].selected)
                {

                    selectedText[selectedCount] = theSelFrom.options[i].text;
                    selectedValues[selectedCount] = theSelFrom.options[i].value;

                    deleteOption(theSelFrom, i);
                    selectedCount++;
                }
            }

            for(i=selectedCount-1; i>=0; i--)
            {
                addOption(theSelTo, selectedText[i], selectedValues[i]);

                var table = document.getElementById("tbl");
                var rowcount = table.rows.length;
                var row = table.insertRow(rowcount);
                document.getElementById("recordCount").value =rowcount;

                var cell1 = row.insertCell(0);
                var bil = document.createTextNode(rowcount);
                cell1.appendChild(bil);

                var cell2 = row.insertCell(1);
                var e1 = document.createElement("INPUT");
                e1.setAttribute("type","text");
                e1.setAttribute("name","report_p_kod_urusan");
                e1.setAttribute("id","report_p_kod_urusan" +(rowcount-1));
    <%--alert("report_p_kod_urusan" + (rowcount-1));--%>
                e1.setAttribute("value",selectedValues[i]);
                cell2.appendChild(e1);
    <%--alert(document.getElementById("report_p_kod_urusan" +(rowcount-1)).value);--%>
            }
        }

    <%--function moveOptions(theSelFrom, theSelTo)
    {

            var selLength = theSelFrom.length;
            var selectedText = new Array();
            var selectedValues = new Array();
            var selectedCount = 0;

            var i;

            for(i=selLength-1; i>=0; i--)
            {

                if(theSelFrom.options[i].selected)
                {

                    selectedText[selectedCount] = theSelFrom.options[i].text;
                    selectedValues[selectedCount] = theSelFrom.options[i].value;

                    deleteOption(theSelFrom, i);
                    selectedCount++;
                }
            }
            var value="";
            for(i=selectedCount-1; i>=0; i--)
            {
                addOption(theSelTo, selectedText[i], selectedValues[i]);
                if(value == "")
                    value = selectedValues[i];
                else
                    value = value+","+selectedValues[i];
            }
            if(selectedCount>=1){
                var table = document.getElementById("tbl");
                var rowcount = table.rows.length;
                var row = table.insertRow(rowcount);
                document.getElementById("recordCount").value =rowcount;

                var cell1 = row.insertCell(0);
                var bil = document.createTextNode(rowcount);
                cell1.appendChild(bil);

                var cell2 = row.insertCell(1);
                var e1 = document.createElement("INPUT");
                e1.setAttribute("type","text");
                e1.setAttribute("name","report_p_kod_urusan");
                e1.setAttribute("id","report_p_kod_urusan" +(rowcount-1));
                e1.setAttribute("value",value);
                cell2.appendChild(e1);
            }

                        
        }--%>

            function deleteOption(theSel, theIndex)
            {
                var selLength = theSel.length;
                if(selLength>0)
                {
                    theSel.options[theIndex] = null;
                }
            }

            function addOption(theSel, theText, theValue)
            {
                var newOpt = new Option(theText, theValue);
                var selLength = theSel.length;
                theSel.options[selLength] = newOpt;
            }

            function hapusOptions(theSelFrom, theSelTo)
            {

                var selLength = theSelFrom.length;
                var selectedText = new Array();
                var selectedValues = new Array();
                var selectedCount = 0;

                var i;

                // Find the selected Options in reverse order
                // and delete them from the 'from' Select.
                for(i=selLength-1; i>=0; i--)
                {
                    if(theSelFrom.options[i].selected)
                    {
    <%--alert(i);--%>
                    selectedText[selectedCount] = theSelFrom.options[i].text;
                    selectedValues[selectedCount] = theSelFrom.options[i].value;

                    document.getElementById('tbl').deleteRow(i+1);
                    document.getElementById("recordCount").value = document.getElementById("recordCount").value-1;
    <%--alert(i+1);--%>
                    deleteOption(theSelFrom, i);

    <%--var table = document.getElementById('tbl');
    var rowCount = table.rows.length;

                document.getElementById("recordCount").value =rowCount-1;

                if(rowCount > 1){
                    for(var j=1;j<rowCount;j++){
                        table.rows[j].cells[j].childNodes[0].data= j;
                    }

                }
    --%>

                    selectedCount++;
                }
            }

            // Add the selected text/values in reverse order.
            // This will add the Options to the 'to' Select
            // in the same order as they were in the 'from' Select.
            for(i=selectedCount-1; i>=0; i--)
            {
                addOption(theSelTo, selectedText[i], selectedValues[i]);
            }
        }

           

        function removeKodUrusan(frm, val) {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var menu = $('#menu').val();
                var url = '${pageContext.request.contextPath}/stripes/pelupusan/utilitiReportNs?deleteSelectedKodUrusan&idCapai='+val+'&idMenu='+menu;
                frm.action = url;
                frm.submit();
            }
        }
        
        function filterBandarPekanMukim() {
            var kodDaerah = $("#report_p_kod_caw").val();
            //            alert(katTanah);        
            $.post('${pageContext.request.contextPath}/pelupusan/utilitiReportNs?senaraiKodBPM&kodDaerah=' + kodDaerah,
            function(data) {
                if (data != '') {
                    $('#partialKodBPM').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.UtilitiReportNSActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:if test="${reportname eq 'DISLTSITE_NS.rdf'}">
                <p>
                    <label><em>*</em>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit1(this.form,'page_div');"/>&nbsp;
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;--%>
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </c:if>

            <%--utiliti report--%>
            <c:if test="${reportname eq 'UTL_MMMCL_NS.rdf' || reportname eq 'UTL_RekodPermitKerajaan_NS.rdf' || reportname eq 'UTL_RekodPermitTMilik_NS.rdf' || reportname eq 'UTL_RekodPajakLombong_NS.rdf' ||  reportname eq 'UTL_RekodBRPajakLombong_NS.rdf' || reportname eq 'UTL_MMMCL_ML.rdf' || reportname eq 'UTL_RekodPermitKM_ML.rdf' || reportname eq 'UTL_RekodPermitPTG_ML.rdf' || reportname eq 'UTL_RekodPermitCG_ML.rdf' || reportname eq 'UTL_RekodBRPermitCG_ML.rdf' || reportname eq 'UTL_RekodPajakLombong_ML.rdf' || reportname eq ' UTL_RekodBRPajakLombong_ML.rdf' || reportname eq ' DISLaporanMohonJMRE_NS.rdf' || reportname eq 'DIS_LAPORAN_RA.rdf'}">

                <p>
                    <label> Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label> Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>

                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <p>
                        <label> Mukim :</label>
                        <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <%--  <p>
                      <label> Hakmilik :</label>
                      <s:select id="hakmilik" name="report_p_kod_hakmilik" style="width:145px;" onchange="doFilterKaunter(this.value);">
                          <s:option value="">--Sila Pilih--</s:option>
                          <s:options-collection collection="${actionBean.listHakmilik}" label="nama" value="kod"/>
                      </s:select>
                  </p> --%>


                <br>

                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit1(this.form,'page_div');"/>&nbsp;
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;--%>
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>

            </c:if>

            <c:if test="${reportname eq 'UTL_PRIZ_NS.rdf' || reportname eq 'UTL_PRIZ_ML.rdf' }">

                <p>
                    <label> Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label> Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>

                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <p>
                        <label> Mukim :</label>
                        <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                    <label> Hakmilik :</label>
                    <s:select id="hakmilik" name="report_p_kod_hakmilik" style="width:145px;" onchange="doFilterKaunter(this.value);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listHakmilik}" label="nama" value="kod"/>
                    </s:select>
                </p> 


                <br>

                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit1(this.form,'page_div');"/>&nbsp;
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;--%>
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>

            </c:if>

            <%--based on parameter kod urusan dan kod daerah--%>
            <c:if test="${reportname eq 'UTL_LURUSAN_NS.rdf'}">
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <!--                    <p>
                                            <label> Mukim :</label>
                    <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                    </s:select>
                </p>-->
                </c:if>
                <p>
                    <label> Tarikh Dari :</label>
                    <s:text id="report_p_date1" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    <%--<s:text id="report_p_date1" name="report_p_date1"/>&nbsp;<font size="1" color="red"> (cth : 2012)</font>--%>
                </p>
                <p>
                    <label> Tarikh Hingga :</label>
                    <s:text id="report_p_date2" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    <%--<s:text id="report_p_date2" name="report_p_date2"/>&nbsp;<font size="1" color="red"> (cth :  2013)</font>--%>
                </p>            

                <p>
                    <label>Kod Urusan :</label>
                    <font size="2" color="Red">
                        <strong>Sila pilih Kod Urusan.</strong>
                    </font>
                </p><br>

                <div align="center">
                    <table align="center">
                        <tr>
                            <td>
                                <s:select name="listKU" id="listKU" multiple="multiple" size="all" style='overflow:scroll; height:300px;' >
                                    <c:forEach items="${actionBean.listKodUrusan}" var="line">
                                        <s:option value="${line.kod}">${line.nama} - (${line.kod})</s:option>
                                    </c:forEach>
                                </s:select>
                            </td>
                            <td>
                                <s:button name="add" value="Tambah >>" class="btn" onclick="moveOptions(this.form.listKU,this.form.selectedUrusan);"/><br><br>
                                <s:button name="remove" value="Hapus <<" class="btn" onclick="hapusOptions(this.form.selectedUrusan,this.form.listKU);"/><br><br>
                                <br><br><br>
                            </td>
                            <td>
                                <s:select name="selectedUrusan" id="selectedUrusan" multiple="multiple" size="all" style='height: 300px; width: 400px;'>
                                </s:select>
                            </td>

                        </tr>
                    </table>

                    <div id="divIdPeranan" style="visibility: hidden; display: none">
                        <input type="text" name="recordCount" id="recordCount" disabled="true"/>
                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <c:set value="0" var="i"/>
                            <tr style="font-weight:bold"></tr>
                            <c:set var="i" value="${i+1}" />

                        </table>
                    </div>

                </div>

                <br>
                <p align="center">
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,'page_div');"/>&nbsp;--%>
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,1);"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>
            </c:if>
            <c:if test="${reportname eq 'REP_PERMOHONAN.rdf'}">
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <p>
                        <label> Mukim :</label>
                        <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label> Tarikh Dari :</label>
                    <s:text id="report_p_date1" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label> Tarikh Hingga :</label>
                    <s:text id="report_p_date2" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>    

                <p>
                    <label>Kod Urusan :</label>
                    <font size="2" color="Red">
                        <strong>Sila pilih Kod Urusan.</strong>
                    </font>
                </p><br>

                <div align="center">
                    <table align="center">
                        <tr>
                            <td>
                                <s:select name="listKU" id="listKU" multiple="multiple" size="all" style='overflow:scroll; height:300px;' >
                                    <c:forEach items="${actionBean.listKodUrusan}" var="line">
                                        <s:option value="${line.kod}">${line.nama} - (${line.kod})</s:option>
                                    </c:forEach>
                                </s:select>
                            </td>
                            <td>
                                <s:button name="add" value="Tambah >>" class="btn" onclick="moveOptions(this.form.listKU,this.form.selectedUrusan);"/><br><br>
                                <s:button name="remove" value="Hapus <<" class="btn" onclick="hapusOptions(this.form.selectedUrusan,this.form.listKU);"/><br><br>
                                <br><br><br>
                            </td>
                            <td>
                                <s:select name="selectedUrusan" id="selectedUrusan" multiple="multiple" size="all" style='height: 300px; width: 400px;'>
                                </s:select>
                            </td>

                        </tr>
                    </table>

                    <div id="divIdPeranan" style="visibility: hidden; display: none">
                        <input type="text" name="recordCount" id="recordCount" disabled="true"/>
                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <c:set value="0" var="i"/>
                            <tr style="font-weight:bold"></tr>
                            <c:set var="i" value="${i+1}" />

                        </table>
                    </div>

                </div>

                <br>
                <p align="center">
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,'page_div');"/>&nbsp;--%>
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,2);"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>
            </c:if>    
            <c:if test="${reportname eq 'UTL_LKEMAJUAN_DIS.rdf'}">
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <p>
                        <label> Mukim :</label>
                        <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label> Tarikh Dari :</label>
                    <s:text id="report_p_date1" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label> Tarikh Hingga :</label>
                    <s:text id="report_p_date2" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>    

                <p>
                    <label>Kod Urusan :</label>
                    <font size="2" color="Red">
                        <strong>Sila pilih Kod Urusan.</strong>
                    </font>
                </p><br>

                <div align="center">
                    <table align="center">
                        <tr>
                            <td>
                                <s:select name="listKU" id="listKU" multiple="multiple" size="all" style='overflow:scroll; height:300px;' >
                                    <c:forEach items="${actionBean.listKodUrusan}" var="line">
                                        <s:option value="${line.kod}">${line.nama} - (${line.kod})</s:option>
                                    </c:forEach>
                                </s:select>
                            </td>
                            <td>
                                <s:button name="add" value="Tambah >>" class="btn" onclick="moveOptions(this.form.listKU,this.form.selectedUrusan);"/><br><br>
                                <s:button name="remove" value="Hapus <<" class="btn" onclick="hapusOptions(this.form.selectedUrusan,this.form.listKU);"/><br><br>
                                <br><br><br>
                            </td>
                            <td>
                                <s:select name="selectedUrusan" id="selectedUrusan" multiple="multiple" size="all" style='height: 300px; width: 400px;'>
                                </s:select>
                            </td>

                        </tr>
                    </table>

                    <div id="divIdPeranan" style="visibility: hidden; display: none">
                        <input type="text" name="recordCount" id="recordCount" disabled="true"/>
                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <c:set value="0" var="i"/>
                            <tr style="font-weight:bold"></tr>
                            <c:set var="i" value="${i+1}" />

                        </table>
                    </div>

                </div>

                <br>
                <p align="center">
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,'page_div');"/>&nbsp;--%>
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,3);"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>
            </c:if>    
            <c:if test="${reportname eq 'UTL_LPREMIUM_DIS.rdf'}">
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <p>
                        <label> Daerah :</label>
                        <s:select id="report_p_kod_caw" name="report_p_kod_caw" style="width:300px;" onchange="filterBandarPekanMukim();">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listKodCaw}" label="name" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label> Mukim :</label>
                        <span id="partialKodBPM">-</span>
                    </p>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    <p>
                        <label> Daerah :</label>
                        ${actionBean.listKodCawByPengguna[0].name}
                        <s:hidden name="report_p_kod_caw" id="report_p_kod_caw" value="${actionBean.listKodCawByPengguna[0].kod}" style="width:300px;"/>

                    </p>
                    <p>
                        <label> Mukim :</label>
                        <s:select id="mukim" name="report_p_mukim" style="width:145px;" onchange="doSomething(this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listBPMByKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label> Tarikh Dari :</label>
                    <s:text id="report_p_date1" name="report_p_date1" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label> Tarikh Hingga :</label>
                    <s:text id="report_p_date2" name="report_p_date2" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>    

                <p>
                    <label>Kod Urusan :</label>
                    <font size="2" color="Red">
                        <strong>Sila pilih Kod Urusan.</strong>
                    </font>
                </p><br>

                <div align="center">
                    <table align="center">
                        <tr>
                            <td>
                                <s:select name="listKU" id="listKU" multiple="multiple" size="all" style='overflow:scroll; height:300px;' >
                                    <c:forEach items="${actionBean.listKodUrusan}" var="line">
                                        <s:option value="${line.kod}">${line.nama} - (${line.kod})</s:option>
                                    </c:forEach>
                                </s:select>
                            </td>
                            <td>
                                <s:button name="add" value="Tambah >>" class="btn" onclick="moveOptions(this.form.listKU,this.form.selectedUrusan);"/><br><br>
                                <s:button name="remove" value="Hapus <<" class="btn" onclick="hapusOptions(this.form.selectedUrusan,this.form.listKU);"/><br><br>
                                <br><br><br>
                            </td>
                            <td>
                                <s:select name="selectedUrusan" id="selectedUrusan" multiple="multiple" size="all" style='height: 300px; width: 400px;'>
                                </s:select>
                            </td>

                        </tr>
                    </table>

                    <div id="divIdPeranan" style="visibility: hidden; display: none">
                        <input type="text" name="recordCount" id="recordCount" disabled="true"/>
                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <c:set value="0" var="i"/>
                            <tr style="font-weight:bold"></tr>
                            <c:set var="i" value="${i+1}" />

                        </table>
                    </div>

                </div>

                <br>
                <p align="center">
                    <%--<s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,'page_div');"/>&nbsp;--%>
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form,4);"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>
            </c:if>    


        </fieldset>
    </div>
</s:form>

