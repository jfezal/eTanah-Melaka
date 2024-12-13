<%-- 
    Document   : rayuan_ansuran_bayaran
    Created on : Jun 23, 2011, 5:31:24 PM
    Author     : Akmal
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
//hide all
   $(document).ready(function() {
       hideAndShow();
       <%--$(".datepicker1").datepicker({dateFormat: 'MM yy'});--%>
   });
  
 function hideTable1() { 
     $('#1').show();
     $('#2').hide();
     $('#3').hide();
     $('#4').hide();
     $('#5').hide();
     $('#6').hide(); 
     $('#bulan2').val(0) ;
     $('#bulan3').val(0) ;
     $('#bulan4').val(0) ;
     $('#bulan5').val(0) ;
     $('#bulan6').val(0) ; 
 }
  function hideTable2() { 
     $('#1').show();
     $('#2').show();
     $('#3').hide();
     $('#4').hide();
     $('#5').hide();
     $('#6').hide(); 
    $('#bulan3').val(0) ;
     $('#bulan4').val(0) ;
     $('#bulan5').val(0) ;
     $('#bulan6').val(0) ; 
 }
  function hideTable3() { 
     $('#1').show();
     $('#2').show();
     $('#3').show();
     $('#4').hide();
     $('#5').hide();
     $('#6').hide(); 
      $('#bulan4').val(0) ;
     $('#bulan5').val(0) ;
     $('#bulan6').val(0) ; 
 }
  function hideTable4() { 
     $('#1').show();
     $('#2').show();
     $('#3').show();
     $('#4').show();
     $('#5').hide();
     $('#6').hide();  
     $('#bulan5').val(0) ;
     $('#bulan6').val(0) ;
 }
  function hideTable5() { 
     $('#1').show();
     $('#2').show();
     $('#3').show();
     $('#4').show();
     $('#5').show();
     $('#6').hide();    
      $('#bulan6').val(0) ;
 }
  function hideTable6() { 
     $('#1').show();
     $('#2').show();
     $('#3').show();
     $('#4').show();
     $('#5').show();
     $('#6').show();    
 }
 
 function hideAndShow() {
     var no = document.getElementById('bulan').value     
     if(no == '1'){
         hideTable1();
     }
       else if(no == '2'){
         hideTable2();
     }
       else if(no == '3'){
         hideTable3();
     }
       else if(no == '4'){
         hideTable4();
     }
       else if(no == '5'){
         hideTable5();
     }
       else if(no == '6'){
         hideTable6();
     }
     else{
         $('#1').hide();
     $('#2').hide();
     $('#3').hide();
     $('#4').hide();
     $('#5').hide();
     $('#6').hide();
     }
     
 }

 function calTarikh() {
    alert("tarikh");
    var date1 = new Date($('#date1').val());
    var date2 = new Date($('#date2').val());
     if($('#date2')<= $('#date1') || $('#date3')<=$('#date2')|| $('#date4')<=$('#date3'))
     {   
         alert("Tarikh tidak sesuai");
         
       
         alert(date2);
         alert(date1);
         if((date2.getMonth()+1) < (date1.getMonth()+1)){
             alert("lusa");
          $('#date2').setMonth( $('#date2').getMonth()+1);
          $('#date3').setMonth( $('#date2').getMonth()+2);
          $('#date4').setMonth( $('#date2').getMonth()+3);
          $('#date5').setMonth( $('#date2').getMonth()+4);
          $('#date6').setMonth( $('#date2').getMonth()+5);
         }
     }
     
     return  $('#date2'),$('#date3'),$('#date4'),$('#date5'),$('#date6');
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


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.RayuanAnsuranActionBean">
    <div class="subtitle">
    <s:messages/>
    <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rayuan Ansuran Bayaran</legend><br/>
            <s:hidden name="ptd"/>
            <s:hidden name="isAnsuranPremium"/>
            
            <c:if test="${actionBean.ptd}">
            <c:if test="${!actionBean.isAnsuranPremium}">
             <p>
                    <label>Jumlah Amaun Yang Perlu Dibayar:</label>
                    <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>                    
             </p>
            <p>
                    <label>Bilangan Bulan Untuk Bayar Secara Ansuran:</label>

                    <s:select name="bilBulan" id="bulan">
                        <s:option value=""> Sila Pilih </s:option>
                        <s:option value="1">1</s:option>
                        <s:option value="2">2</s:option>
                        <s:option value="3">3</s:option>
                        <s:option value="4">4</s:option>
                        <s:option value="5">5</s:option>
                        <s:option value="6">6</s:option>
                    </s:select>
             </p>
             <br/>
              <p align="center">
             
                <s:button name="simpanBulan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p> 
             </c:if>             
             <c:if test="${actionBean.isAnsuranPremium}">                 
                 <br/></br>
             <p>
                    <label>Jumlah Amaun Yang Perlu Dibayar:</label>
                    <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>
                   <%--${actionBean.permohonanTuntutanKos.amaunTuntutan}--%> 
             </p>
            <p>
                    <label>Bilangan Bulan Untuk Bayar Secara Ansuran:</label>
                    <s:hidden name="bilBulan" id="bulan"/>
                    ${actionBean.bilBulan} Bulan
             </p>
            <div class="content" align="center">
               <%--<table class="tablecloth">
                  <tr><th></th><th>Bayaran</th><th>Tarikh Akhir Bayaran</th></tr>
                  
                  <tr id="1"><th>1. </th><td>RM <s:text name="bulan1" id="bulan1" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran1" id="date1" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/></td></tr>
                  <tr id="2"><th>2. </th><td>RM <s:text name="bulan2" id="bulan2" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran2" id="date2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/></td></tr>
                  <tr id="3"><th>3. </th><td>RM <s:text name="bulan3" id="bulan3" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran3" id="date3" class="datepicker" formatPattern="dd/MM/yyyy"/></td></tr>
                  <tr id="4"><th>4. </th><td>RM <s:text name="bulan4" id="bulan4" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran4" id="date4" class="datepicker" formatPattern="dd/MM/yyyy"/></td></tr>
                  <tr id="5"><th>5. </th><td>RM <s:text name="bulan5" id="bulan5" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran5" id="date5" class="datepicker" formatPattern="dd/MM/yyyy"/></td></tr>
                  <tr id="6"><th>6. </th><td>RM <s:text name="bulan6" id="bulan6" formatPattern="#,##0" onkeyup="validateNumber(this,this.value);"/></td><td><s:text name="tarikhAkhirBayaran6" id="date6" class="datepicker" formatPattern="dd/MM/yyyy"/></td></tr>
               </table>--%>
               <table class="tablecloth">
                   <tr><th>Bil</th><th>Ansuran</th><th>Bulan</th><th width="220px;">Keterangan</th><th width="100px;" colspan="2">Bayaran</th></tr>

                  <tr id="1"><th>1. </th><td>Pertama </td><td><s:text name="tarikhAkhirBayaran1" id="date1" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/></td>
                      <td colspan="3">
                          <table  border="1">
                                   <tr><td>Premium </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><s:text name="bulan1" id="bulan1" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td>
                                    </tr>
                                    <tr><td width="230px;">Sumbangan Saliran </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbanganSaliran}"/></td>
                                    </tr>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'SBMS'}">
                                    <tr><td>Hasil Tahun Pertama </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilTahunPertama}"/></td>
                                    </tr>
                                    <tr><td>Bayaran Wang Pelan </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranPelan}"/></td>
                                    </tr>                                    
                                    <tr><td>Bayaran Hakmilik Semantara &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranSemantara}"/></td>
                                    </tr>
                                    <tr><td>Bayaran Hakmilik Kekal </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranKekal}"/></td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSS' ||
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSN' ||
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKKT' ||
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKSN'}">
                                        <tr><td>Pendaftaran </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pendaftaran}"/></td>
                                    </tr>
                                    </c:if>

                                    <tr><th>Jumlah</th>
                                        <th>RM</th>
                                        <th style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah}"/></th>
                                    </tr>
                          </table>
                      </td>
                      </tr>
                  <tr id="2"><th>2. </th><td>Kedua   </td><td><s:text name="tarikhAkhirBayaran2" id="date2" class="datepicker" formatPattern="dd/MM/yyyy"/></td><td>Premium </td><td> RM</td><td><s:text name="bulan2" id="bulan2" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td></tr>
                  <tr id="3"><th>3. </th><td>Ketiga  </td><td><s:text name="tarikhAkhirBayaran3" id="date3" class="datepicker" formatPattern="dd/MM/yyyy"/></td><td>Premium </td><td> RM</td><td><s:text name="bulan3" id="bulan3" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td></tr>
                  <tr id="4"><th>4. </th><td>Keempat </td><td><s:text name="tarikhAkhirBayaran4" id="date4" class="datepicker" formatPattern="dd/MM/yyyy"/></td><td>Premium </td><td> RM</td><td><s:text name="bulan4" id="bulan4" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td></tr>
                  <tr id="5"><th>5. </th><td>Kelima </td><td><s:text name="tarikhAkhirBayaran5" id="date5" class="datepicker" formatPattern="dd/MM/yyyy"/></td><td>Premium</td><td>RM</td><td><s:text name="bulan5" id="bulan5" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td></tr>
                  <tr id="6"><th>6. </th><td>Keenam  </td><td><s:text name="tarikhAkhirBayaran6" id="date6" class="datepicker" formatPattern="dd/MM/yyyy"/></td><td>Premium &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td> RM</td><td> <s:text name="bulan6" id="bulan6" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" style="width:120px;text-align:right"/></td></tr>
                  <tr><th colspan="4" align="left">JUMLAH KESELURUHAN:</th><th>RM</th><th style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahKeseluruhan}"/> </th></tr>
                  
               </table>

           </div>
           <p align="center">
             
                <s:button name="simpanAnsuran" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
           </p> 
           </c:if>
           </c:if>

           <c:if test="${!actionBean.ptd}">    
             <%--  <c:if test="${!actionBean.view}">  --%>
             <br/>
             <p>
                    <label>Jumlah Amaun Yang Perlu Dibayar:</label>
                     RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>                   
             </p>
             <p>
                    <label>Bilangan Bulan Untuk Bayar Secara Ansuran:</label>
                    <s:hidden name="bilBulan" id="bulan"/>
                    ${actionBean.bilBulan} Bulan
             </p>
            <div class="content" align="center">               
               <table class="tablecloth">
                  <tr><th>Bil</th><th>Ansuran</th><th>Bulan</th><th width="300px;">Keterangan</th><th width="100px;" colspan="2">Bayaran</th></tr>
                  <tr id="1"><th>1. </th><td>Pertama </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran1}"/></td>
                      <td colspan="3">
                          <table  border="1">
                                   <tr><td>Premium </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan1}"/></td>
                                    </tr>
                                    <tr><td width="230px;">Sumbangan Saliran</td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbanganSaliran}"/></td>
                                    </tr>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'SBMS'}">
                                    <tr><td>Hasil Tahun Pertama </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilTahunPertama}"/></td>
                                    </tr>
                                    <tr><td>Bayaran Wang Pelan </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranPelan}"/></td>
                                    </tr>                                    
                                    <tr><td>Bayaran Hakmilik Semantara &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranSemantara}"/></td>
                                    </tr>
                                    <tr><td>Bayaran Hakmilik Kekal </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranKekal}"/></td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSS' || 
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSN' ||
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKKT' ||
                                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKSN'}">
                                        <tr><td>Pendaftaran </td>
                                        <td>RM</td>
                                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pendaftaran}"/></td>
                                    </tr>
                                    </c:if>
                                    <tr><th>Jumlah</th>
                                        <th>RM</th>
                                        <th style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah}"/></th>
                                    </tr>
                          </table>
                      </td>
                      </tr>
                  <tr id="2"><th>2. </th><td>Kedua   </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran2}"/></td><td>Premium </td><td> RM</td><td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan2}"/></td></tr>
                  <tr id="3"><th>3. </th><td>Ketiga  </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran3}"/></td><td>Premium </td><td> RM</td><td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan3}"/></td></tr>
                  <tr id="4"><th>4. </th><td>Keempat </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran4}"/></td><td>Premium </td><td> RM</td><td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan4}"/></td></tr>
                  <tr id="5"><th>5. </th><td>Kelima  </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran5}"/></td><td>Premium</td><td>RM</td><td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan5}"/></td></tr>
                  <tr id="6"><th>6. </th><td>Keenam  </td><td><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhAkhirBayaran6}"/></td><td>Premium &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td> RM</td><td style="text-align:right"> <fmt:formatNumber pattern="#,##0.00" value="${actionBean.bulan6}"/></td></tr>
                  <tr><th colspan="4" align="left">JUMLAH KESELURUHAN:</th><th>RM</th><th style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahKeseluruhan}"/> </th></tr>
               </table>
               <br><br>
                
           </div>  
               </c:if>  
          <%-- </c:if> --%>
          
           <%--
           <c:if test="${actionBean.view}">
               <c:if test="${!(actionBean.ptd)}">
                   <c:if test="${!(actionBean.isAnsuranPremium)}">
             <p>
                    <label>Jumlah Amaun Yang Perlu Dibayar:</label>
                    <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>
                   
             </p>
             <p>
                    <label>Bilangan Bulan Untuk Bayar Secara Ansuran:</label>
                    <s:hidden name="bilBulan" id="bulan"/>
                    ${actionBean.bilBulan} Bulan
             </p>
             <br/>            
                   </c:if>
               </c:if>
           </c:if>
           --%>
             
              

        </fieldset>
    </div>
 </s:form>
   
