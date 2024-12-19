<%--
    Document   : betulTarikhLuput
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>

<script type="text/javascript">
    
    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 45 || charCode > 57 || charCode == 47))
            return false;

        return true;
    }
  
    function filterTempoh(idH,i){
        var tempoh = $("#tempoh"+i).val();
        if(tempoh > 99)
        {
            alert('Tempoh Pegangan Hakmilik : '+[idH]+' yang dimasukkan lebih dari 99 tahun');
            $('#tempoh'+i).val("");
            return false;
        }
        else
        {
            $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraTempoh&idHakmilik='+idH+'&tempoh='+tempoh,
            function(data){
                if(data != ''){
                    $('#tarikhluputbaru'+i).val(data);
                }
            }, 'html');
        }
    }

    function filterDate(idH,i){
        var tarikh = $("#tarikhluputbaru"+i).val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraDate&idHakmilik='+idH+'&tarikh='+tarikh,
        function(data){
            if(data != ''){
                $('#tempoh'+i).val(data);
            }
        }, 'html');

    }

 function doCalcEndDate(id, startDate, i){   
    

        if($('#' +id).val() == '') {
            return;
        }        
        
        var thn = parseInt($('#' +id).val()) ;            
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(0, 4),10);        
        var m = parseInt(startDate.substring(5, 7),10);        
        var d = parseInt(startDate.substring(8, 10),10);     
        var r = new Date();
        //alert(y);
        //alert(m);
        //alert(d);
        //r.setFullYear(y, m, d);
       
          d = d -1;
           if (d ==0){m = m-1;}
        
        if(!isNaN(thn)){
            y = y + thn;
        }
        
        //y = y + thn;
        var endDate = new Date();        
        endDate.setDate(d);
        endDate.setMonth(m-1);
        endDate.setFullYear(y);        
        $('#tarikhluputbaru' + i).val(endDate.format("dd/mm/yyyy"));
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tarikh Luput
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <div class="content" align="center">
                Sila Masukkan Tarikh Luput Baru atau Tempoh Pegangan Baru
                <br>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                    <c:set var="date"/>
                    <c:if test="${line.hakmilik.tarikhDaftarAsal eq null}">
                        <display:column  title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}" property="hakmilik.tarikhDaftar" />
                        <c:set var="date" value="${line.hakmilik.tarikhDaftar}"/>
                    </c:if>
                    <c:if test="${line.hakmilik.tarikhDaftarAsal ne null and line.hakmilik.tarikhDaftar eq line.hakmilik.tarikhDaftarAsal}">
                        <display:column  title="Tarikh Daftar Asal" format="{0,date,dd/MM/yyyy}" property="hakmilik.tarikhDaftar"/>
                        <c:set var="date" value="${line.hakmilik.tarikhDaftar}"/>
                    </c:if>
                    <c:if test="${line.hakmilik.tarikhDaftarAsal ne null and line.hakmilik.tarikhDaftar ne line.hakmilik.tarikhDaftarAsal}">
                        <display:column  title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}" property="hakmilik.tarikhDaftarAsal"/>
                        <c:set var="date" value="${line.hakmilik.tarikhDaftarAsal}"/>
                    </c:if>
                    <display:column property="hakmilik.tarikhLuput"  title="Tarikh Luput Lama" format="{0,date,dd/MM/yyyy}"/>
                    <display:column property="hakmilik.tempohPegangan"  title="Tempoh Pegangan Lama  (Tahun)"/>
                    <display:column title="Tarikh Luput Baru">
                        <s:text name="tarikhluputbaru[${line_rowNum-1}]" class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhluputbaru${line_rowNum-1}" onchange="filterDate('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/>
                    </display:column>
                    <display:column title="Tempoh Pegangan Baru (Tahun)">
                        <s:text name="tempohPeganganBaru[${line_rowNum-1}]"  id="tempoh${line_rowNum-1}" onchange="doCalcEndDate(this.id, '${date}','${line_rowNum-1}'); "/>
                    </display:column>
                </display:table>
                <br>
                <p>
                    <s:button name="saveTarikhLuput" value="Simpan" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </p>
                <br/>
            </div>
            <br/>
        </fieldset>
    </div>
</s:form>