<%--
    Document   : jadual_kiraPremium
    Created on : Mar 21, 2011, 8:25:00 PM
    Author     : Rohans
--%>

<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">
        //add by Shazwan 09 June 2011
        function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
        cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
  
     function validateForm(){ 
        var kt = '${actionBean.kodTanah}';
        var kkt = '${actionBean.kodKegunaanTanah}';
        if(kt==""){
            alert("Sila masukkan KodTanah");
            return false;
        }
        if(kkt==""){
            alert("Sila masukkan KodKegunaanTanah");
            return false;
        }
        if(document.form2.premium1.value=="0")
        {
            alert("Sila masukkan Premium");
            return false;
        }

             return true;

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


      function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
      }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
      }
     
     function doCalc(){
         if (!isNaN(${actionBean.premium})) {
     <%--          $('#premium1').val('${actionBean.premium}');--%> 
    <%--           alert("Nilai Premium = RM" +${actionBean.peratus}+ " X " +${actionBean.harga}+ " X " +${actionBean.pasaran} ) --%> 
         } else {
            alert("Tanah bukan tanah Bandar, Pekan, Desa atau Hasil Pengambilan")
         }   
     }
     
 </script>
<s:form beanclass="etanah.view.stripes.pelupusan.JadualPBMTActionBean" >
    <s:messages/>
    <s:errors/>
    <div class="subtitle" >
        <fieldset class="aras1">
            <legend>
                JADUAL PENGIRAAN PREMIUM
            </legend><br>
            <p><label>Keluasan Dipohon :</label><s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>  &nbsp; ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</p>
            <p><label>Keluasan Diluluskan :</label><s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasDiluluskan}"/>  &nbsp;${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</p>    
            <p><label>Mukim :</label>${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</p>
            <p><label>Jenis Hakmilik :</label>
             <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.nama eq null}">
               Tiada
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.nama ne null}">
               ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
            </c:if>
             </p>
             <c:if test="${actionBean.hakmilikPermohonan.tempohPajakan ne null}">
                 <p><label>Tempoh Pajakan :</label>${actionBean.hakmilikPermohonan.tempohPajakan} &nbsp; tahun</p>
             </c:if>
            
            <p><label>Jenis Tanah :</label>${actionBean.permohonanLaporanPelan.kodTanah.nama}</p>
            <p><label>Kegunaan Tanah :</label>${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}</p>
            <p><label>Harga Pasaran :</label><fmt:formatNumber pattern="#,#00.00" value="${actionBean.permohonanRujukanLuar.nilai}"/></p>
            <p><label>Hasil :</label>${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</p>     
            <p><label>Bayaran Ukur :</label>Mengikut P.U.(A) 438</p>
            <p><label>Penjenisan :</label>${actionBean.kodKategori}</p>
            <p><label>Syarat Nyata :</label>${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</p>
            <p><label>Sekatan Kepentingan :</label>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}-</p>
           
            <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod eq null}">
                <!--<p><label>&nbsp;</label><s:textarea name="" value="" class="normal_text" rows="3" cols="100" readonly="true"/></p>-->
            </c:if>  
            <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod ne null}">
                <p><label>&nbsp;</label><s:textarea name="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}" class="normal_text" rows="3" cols="100" readonly="true"/></p>
            </c:if>
            
            <p><label>Kadar Premium Yang Diluluskan MMKN :</label>${actionBean.hakmilikPermohonan.keteranganKadarPremium}</p>
             
        <c:if test="${actionBean.hakmilikPermohonan.kadarPremium eq null}">
            <p><label>Nilai Premium :</label><s:text name="premium" formatPattern="#,#00.00" id="premium1" onkeyup="validateNumber(this,this.value);" value="${actionBean.hakmilikPermohonan.kadarPremium}"/>
                <!--s:button name="kiraNilaiPremium" id="kiraNilaiPremium" value="Kira" class="btn" onclick="doCalc()"/--></p>
        </c:if>
        <c:if test="${actionBean.hakmilikPermohonan.kadarPremium ne null}">
            <p><label>Nilai Premium :</label><s:text name="premium" formatPattern="#,#00.00" onblur="this.value=formatCurrency(this.value);" id="premium1" onkeyup="validateNumber(this,this.value);" value="${actionBean.hakmilikPermohonan.kadarPremium}"/><%--<s:text name="hakmilikPermohonan.kadarPremium" formatPattern="#,##0.00" onblur="alert(this.value=formatCurrency(this.value));" id="premium1" onkeyup="validateNumber(this,this.value);"/>--%></p>
        </c:if>
            <p><label>&nbsp;</label><s:button name="simpan" id="save" size="2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>
        
       </fieldset>
    </div>
 <br/><br/>
               
</s:form>
