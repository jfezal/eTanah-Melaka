<%--
    Document   : kertas_stasatan
    Created on : Nov 18, 2011, 2:28:49 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <script type="text/javascript">

       function sumTotValue(){
           var x= document.getElementById('line');
         var rowCount = x.rows.length;
         var j=1;
       	 var totCount =0;
         var totalPihak = '';
      	 for(var i=1; i<rowCount; i++) {
             var jumlah = document.getElementById("nilai"+i).value;
             totCount = parseInt(totCount) + parseInt(jumlah);
              
         }
        document.getElementById("totCount").value= totCount;
       }

        function doBayaran(event, f)
    {

         var x= document.getElementById('line');
         var rowCount = x.rows.length;
         alert("rowCount"+rowCount);
         var j=1;
       	 var count = 0;
         var totalPihak = '';
      	 for(var i=1; i<rowCount; i++) {
             var jumlah = document.getElementById("nilai"+i).value;
             var pihakId = document.getElementById("pihak"+i).value;
             var combinedValue = jumlah+'_'+pihakId;
             var selectedpihak = document.getElementById("selectedpihak").value;
			count++;
			if(count ==1){
				selectedpihak = combinedValue;
			}else{
				selectedpihak = selectedpihak +","+combinedValue;
			}
			document.getElementById("selectedpihak").value = selectedpihak;


        }
        var selectedpihak_ref = document.getElementById("selectedpihak").value;
           alert("selectedpihak"+selectedpihak_ref);
           var q = $(f).formSerialize();
        var url = f.action + '?' + event +'&selectedpihak=' + selectedpihak_ref;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

        }
       jQuery.fn.ForceNumericOnly = function() {
       return this.each(function()     {
           $(this).keydown(function(e)         {
               var key = e.charCode || e.keyCode || 0;             // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                return (
                key == 8 ||
                    key == 9 ||
                    key == 46 ||
                    (key >= 37 && key <= 40) ||
                    (key >= 48 && key <= 57) ||
                    (key >= 96 && key <= 105));
            });
        });
    };
      jQuery('.numbersOnly').ForceNumericOnly();
        function doBayaran()
    {

         var x= document.getElementById('line');
         var rowCount = x.rows.length;
         var j=1;
       	 var count = 0;
         var totalPihak = '';
      	 for(var i=1; i<rowCount; i++) {
             var jumlah = document.getElementById("nilai"+i).value;
             var pihakId = document.getElementById("pihak"+i).value;
             var combinedValue = jumlah+'_'+pihakId;
             var selectedpihak = document.getElementById("selectedpihak").value;
			count++;
			if(count ==1){
				selectedpihak = combinedValue;
			}else{
				selectedpihak = selectedpihak +","+combinedValue;
			}
			document.getElementById("selectedpihak").value = selectedpihak;


        }
        var selectedpihak_ref = document.getElementById("selectedpihak").value;
            var url = '${pageContext.request.contextPath}/pelupusan/bayanan_pampasan?updateSimpan&selectedpihak='
                +selectedpihak_ref;
                $.get(url,
            function(data){
                $('#page_div').html(data);
//                refreshlptn();
            },'html');


        }
        function refreshlptn(){
        var url = '${pageContext.request.contextPath}/pelupusan/bayanan_pampasan?refreshpage';
        $.get(url,
        function(data){
        $('#page_div').html(data);
        },'html');
        }
                </script>

    </head>
    <body>
       <s:form beanclass="etanah.view.stripes.pelupusan.BayananPampasanActionBean">
           <s:messages/>
     <fieldset class="aras1">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Maklumat Bayaran Pampasan</b> </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Id Hakmilik:</label>${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}</td>

                    </tr>
                    <tr>
                         <td align="left" colspan="4"><label>No Lot:</label>
                            ${actionBean.hakmilikPermohonan.noLot}

                        </td>
                    </tr>
                    <tr>

                        <td align="left" colspan="4"><label>Luas:</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.kod}
                        </td>
                    </tr>
                    <tr>

                        <td align="left" colspan="4"><label>Nilaian (RM):</label>
                            ${actionBean.permohonanRujukanLuar.nilai}
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <s:hidden name="selectedpihak" id="selectedpihak" />
                            <fieldset class="aras1">
                            <div class="content" align="center">
                                 <c:set var="count" value="0" />
                            <display:table class="tablecloth" name="${actionBean.mohanPihakList}" pagesize="5"
                                           requestURI="/pelupusan/bayanan_pampasan" id="line">
                                <display:column title="Bil" style="width:300px" >${line_rowNum}</display:column>
                                <display:column title="Nama" style="width:300px" property="pihak.nama"></display:column>
                                <display:column title="No Pengenalan" style="width:300px"  >${line.pihak.noPengenalan}</display:column>
                                <display:column title="Bahagian"  style="width:300px"  >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                                <display:column title="Jumlah (RM)" style="width:300px" total="true"  >
                                    <c:if test="${line.nilai ne null && line.nilai ne 0}">
                                        <c:set var="count" value="${count+(line.nilai)}" />
                                        <s:text name="nilai${line_rowNum}" id="nilai${line_rowNum}" value='${line.nilai}' onkeyup="sumTotValue()" class="numbersOnly"/>
                                    </c:if>
                                    <c:if test="${line.nilai eq null || line.nilai eq 0}">
                                        <c:set var="count" value="${count+((line.syerPembilang/line.syerPenyebut)*actionBean.permohonanRujukanLuar.nilai)}" />
                                        <s:text name="nilai${line_rowNum}" id="nilai${line_rowNum}" value='${(line.syerPembilang/line.syerPenyebut)*actionBean.permohonanRujukanLuar.nilai}' onkeyup="sumTotValue()" class="numbersOnly"/></c:if>
                                    
                                    <s:hidden name='pihak${line_rowNum}' id='pihak${line_rowNum}' value='${line.idPermohonanPihak}'></s:hidden></display:column>
                            </display:table>
                                <p >
                                <label >&nbsp;</label>
                                <label >Jumlah (RM)</label>
                                <s:text name="totCount" id="totCount" value="${count}" readonly="true"></s:text>
                                </p>
                            </div>
                            </fieldset>
                        </td>
                    </tr>
                    
                    <tr>

                        <td colspan="4" align="center">
                            <s:button name="updateSimpan" value="Simpan" class="btn" onclick="doBayaran(this.name, this.form)"/>
                        </td>
                    </tr>
                   </table>

    </fieldset>
  </s:form>
    </body>
</html>
