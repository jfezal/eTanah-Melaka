<%-- 
    Document   : test1
    Created on : May 27, 2011, 12:02:53 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<%--  <s:hidden name="${actionBean.senaraiRujukanLuar}" id ="sRL"/>--%>
<script type = "text/javascript">
            
    $(document).ready( function() {
                
        $('#buttontandatangan').hide();
        <c:if test="${fn:length(actionBean.senaraiRujukanLuar) == 0}" >
                $('#hantar').attr("disabled", "true");
        </c:if>
        <c:if test="${fn:length(actionBean.senaraiRujukanLuar) > 0}" >
                $('#hantar').removeAttr("disabled");
        </c:if>    
            
        
    });
            
    function search(index){
    <%--alert("search:"+index);--%>
            var url = '${pageContext.request.contextPath}/pelupusan/sedia_jabatan?kodAgensiPopup&index='+index;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }
        function search1(kod,idRujukan){
            //                alert("Kod Agensi :" + kod);
            //                alert("Id Rujukan :" + idRujukan);
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pelupusan/sedia_jabatan?edit&kod='+kod+'&idRujukan='+ idRujukan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }

        function removePermohonanRujukanLuar(idRujukan,row){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pelupusan/sedia_jabatan?deleteRujukan&idRujukan='
                    +idRujukan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
            
        function savetolist(){
            var li1=document.write(sRL);
            var input=document.createElement('INPUT');
            input.setAttribute("type","text");
            input.setAttribute("name","salinanKod");
            input.setAttribute("id","salinanKod");
            li1.appendChild(input);

            var li2=document.write(sRL);
            var input2=document.createElement('INPUT');
            input2.setAttribute("type","text");
            input2.setAttribute("name","salinanKepada");
            input2.setAttribute("id","salinanKepada");
            li2.appendChild(input2);}
            
            
          
            
        //            function kemaskini(kod) {
        //                   alert(kod) ;
        //                    var url = '${pageContext.request.contextPath}/pelupusan/sedia_jabatan?edit&kod=' + kod;
        //                    $.get(url,
        //                    function(data){
        //                        $('#page_div').html(data);
        //                    },'html');
                
            
    <%--   $(document).ready( function() {

            $('.agensi.nama'+i).click( function() {
                window.open("${pageContext.request.contextPath}/agensi?name="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }--%>


            function refreshAgensi(){
                var url = '${pageContext.request.contextPath}/pelupusan/sedia_jabatan?refreshPage';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

            function test() {
    
                if($('#namapguna').val() != ""){
                    $('#buttontandatangan').show();
                    //        alert($('#namapguna').val()) ;
                    //        var ditundatangan = $('#namapguna').val() ;
                    //        alert(ditundatangan) ;
                    //        
                    //        document.form.testing.value = ditundatangan ;
                    //        var a = $('#testing').val()
                    //        alert(a) ;
                    //         var url = ' ${pageContext.request.contextPath} /pelupusan/sedia_jabatan?simpanTandatangan';
                    //                    $.get(url,
                    //                    function(data){
                    //                        $('#page_div').html(data);
                    //                    },'html');
                }

            }

            
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean" name="form">
    <s:messages/>

    <fieldset class="aras1">
        <div align="left">

            <s:hidden name="testing" id="testing"/><p><label>Ditandatangan Oleh :</label></p>
            <s:select name="ditundatangan" id="namapguna" disabled="true">
                                           <s:option label="Sila Pilih" value="" />                          
                                           <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                                                   <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                               </c:forEach>
                                       </s:select>
                <div id="buttontandatangan" align="center">
            </div>
            <div class="content" align="center"> 
                <c:set value="0" var="i"/>
                <c:set value="0" var="k"/>     
                <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/sedia_jabatan">

                    <display:column title ="Bil">${line_rowNum}
                        <s:hidden name="" class="${line_rowNum -1}" value="${line.idRujukan}"/>
                    </display:column>
                    <display:column  title ="Nama Jabatan/ADUN">
                        <u>${line.agensi.nama}</u>
                    </display:column>
                    <display:column title ="Catatan" property="catatan"/>
                    <display:column title ="Tarikh Hantar" ><s:format value="${line.tarikhDisampai}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title ="Jangka Masa(Hari)" value="14"/>
                    <display:column title ="Tarikh Jangka Terima"><s:format value="${line.tarikhJangkaTerima}" formatPattern="dd/MM/yyyy"/></display:column> 
                    
                         <c:set var="k" value="${k+1}" />
                    <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
                </display:table>
                        
            </div>

    </fieldset>
</s:form>





