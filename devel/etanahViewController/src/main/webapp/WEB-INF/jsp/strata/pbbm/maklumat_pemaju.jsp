<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

function addNewPemaju(){
            window.open("${pageContext.request.contextPath}/urusan_pbbm?showForm17", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
        }

</script>
<s:form beanclass="etanah.view.strata.urusan_pbbm">
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemaju</legend>
            <p>
                <label>Pemaju : </label> <s:select name=""/>    <s:button onclick="addNewPemaju();" class="longbtn" name="" value="Tambah Pemaju"/>

            </p>
              <p>
                <label>Nyatakan Lain-lain :</label><s:text name="lain"/>

            </p>
            <p>
                <label>*Nama Projek :</label><s:text name="nama_projek"/>

            </p><br>
             <p> <label>&nbsp;</label>
              <a href="urusan?showForm31"<s:submit name="Simpan" value="Simpan" class="btn"/></a>
                <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>
                        </p>
                  </fieldset>

          
    </div>

    </s:form>