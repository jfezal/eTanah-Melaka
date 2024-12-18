<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : Sreenivasa Reddy Munagal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

    }
    function editPemohon(val){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?editpemohonPopup&idPihak="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }

     function clearForm(){
     <%--alert("--reset--");--%>
         var url = "${pageContext.request.contextPath}/strata/jana?resetForm";
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');                    
                    <%--$('#ulasanJupem').val('');--%>
     }

</script>
<s:form beanclass="etanah.view.strata.pageForViewJana">
   <s:messages/>
    <s:errors/>

      <div class="subtitle">
        <fieldset class="aras1">
           <legend>Penerima Pelan dan Ulasan dari JUPEM </legend>&nbsp;
            <p>
                <label>Ulasan JUPNS:</label>
                <s:textarea name="ulasanJupem" id="ulasanJupem" value="" cols="50" rows="5" class="normal_text"></s:textarea>
            </p>
            <p>
                <label>&nbsp;</label> 
                <s:button class="btn" value="Simpan" name="simpanUlasan" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>
            </p>
        </fieldset></div>
            &nbsp;
    &nbsp;&nbsp;
</s:form>