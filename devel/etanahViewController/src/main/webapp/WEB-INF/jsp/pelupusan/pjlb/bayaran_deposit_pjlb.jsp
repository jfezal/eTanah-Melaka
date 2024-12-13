<%-- 
    Document   : bayaran_deposit_pjlb
    Created on : Jul 21, 2013, 1:56:42 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<script type="text/javascript">
   $(document).ready( function() {   
       // if('${actionBean.permohonan.kodUrusan.kod}' == "PLPS"){
         //   if('' == "LN"){
                
            //    $('#catatanKwsn').show();
          //  }else
          //      $('#catatanKwsn').hide();
               
       // } 
           
    });
    
    function calculateSyarat(){
        var f_amaunSebenar1 = document.getElementById('f_amaunSebenar').value;
        //alert(kuantiti);
               
        var deposit = f_amaunSebenar1*0.2;
            
        document.getElementById('f_deposit').value = CurrencyFormatted(deposit);
    }
    function CurrencyFormatted(deposit)
    {
        var i = parseFloat(deposit);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }
    
    
    
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
   
    
    

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.BayaranDepositPJLBActionBean">
    
    <div class="subtitle">
        <fieldset class="aras1" align="center">
            <s:messages/>
            <s:errors/>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
            <legend>
                Bayaran Deposit Lesen Pajakan Lombong        
            </legend>
            </c:if>
            <p>
                <label>Rujukan Fail :</label>
                ${actionBean.idPermohonan}
                &nbsp;

            </p>
<!--            <p>
                <label>No. Lot/PT :</label>
                ${actionBean.noLot}
                &nbsp;

            </p>
            <p>
                <label>Bandar/ Kampung/ Mukim :</label>
                ${actionBean.bpm}
       
                &nbsp;

            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}

            </p>          -->
            <p>
                <label>Amaun Sebenar :</label>
                RM <s:text name="mohonTuntutKos.amaunSebenar" id="f_amaunSebenar" formatPattern="###,###,###.00" value="${actionBean.amaunSebenar}" onkeypress="calculateSyarat()"/>      
            </p>
            <p>
                <label>Bayaran :</label>
                RM <s:text name="mohonTuntutKos.amaunTuntutan" id="f_deposit" formatPattern="###,###,###.00" readonly="true"/>      
            </p>

            <br/><br/>
                <center><s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></center>
        </fieldset>
    </div>
</s:form>


