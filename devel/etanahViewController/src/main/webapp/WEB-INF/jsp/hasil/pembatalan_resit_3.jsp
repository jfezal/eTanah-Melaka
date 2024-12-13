<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/js/jquery.form.js"></script>


<script type="text/javascript">
    function bal(f, x, row, resit){
        <%--alert("resit :"+resit);--%>
        if(confirm("Anda pasti untuk pindah ke no. akaun :"+x)){
      
        var queryString = $(f).formSerialize();
<%--        $.get("${pageContext.request.contextPath}/hasil/pembatalan_resit?saveNoAkaun&"+queryString+"&account="+x+"&amaun="+row+"&idDokumenKewangan="+resit,
        setTimeout(function(){
            self.opener.kesegaranBatalResit(resit);
            self.close();
        }, 100));--%>
                var url = "${pageContext.request.contextPath}/hasil/pembatalan_resit?saveNoAkaun&"+queryString+"&account="+x+"&amaun="+row+"&idDokumenKewangan="+resit;
                $.get(url,queryString,
                function(data){
                    alert(data);
                    $('#page_div').html(data);
                    self.opener.kesegaranBatalResit(resit);
                    self.close();
                },'html');
         }
    }

    function tutup(resitNo){
        <%--alert("resitNo :"+resitNo);--%>
        self.opener.kesegaranBatalResit(resitNo);
        self.close();
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.PembatalanResitActionBean">

<%--    <fieldset class="aras1">

         <c:if test="${actionBean.kodNegeri eq 'melaka'}">
        <legend>Carian Akaun</legend>
        <p class=instr>
            <font color="red">PERINGATAN:</font> Sila cari No Akaun.
        </p>


        <p>
            <label> No Akaun :</label>
            <s:text name="akaunKredit.noAkaun" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
        </p>
         </c:if>
          <c:if test="${actionBean.kodNegeri ne 'melaka'}">
        <legend>Carian Hakmilik</legend>
        <p class=instr>
            <font color="red">PERINGATAN:</font> Sila masukkan ID Hakmilik.
        </p>


        <p>
            <label> ID Hakmilik :</label>
            <s:text name="hakmilik.idHakmilik" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
        </p>
         </c:if>

        <div align="right">
            <s:submit name="cariAkaun" value="Cari" class="btn" />
            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_hakmilik');" />
        </div>
    </fieldset>
--%>

    <%--<c:if test="${actionBean.flag}">--%>
    <s:errors/>
    <s:messages/>

        <br>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Akaun
                </legend>
                <div class="content" align="center">


                    <display:table class="tablecloth" name="${actionBean.dkList}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >

                        <display:column title="Pilih" style="text-align:center">

                            <div align="center"><s:radio name="account" value="${line.dokumenKewangan.akaun.noAkaun}" onclick="bal(this.form, '${line.dokumenKewangan.akaun.noAkaun}','${line.dokumenKewangan.amaunBayaran}','${actionBean.dokumenKewangan.idDokumenKewangan}')"/></div>
                        </display:column>
                        <%--<display:column  title="No Resit" property="dokumenKewangan.idDokumenKewangan" />--%>
                        <display:column  title="ID Hakmilik" property="dokumenKewangan.akaun.hakmilik.idHakmilik" />
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <display:column  title="No Akaun" property="dokumenKewangan.akaun.noAkaun" />
                        </c:if>
                        <%--<display:column  title="Amaun (RM)" property="dokumenKewangan.amaunBayaran" format="{0,number, 0.00}" style="text-align:right" />--%>

                    </display:table>
                    <%--<br>
                    <p align="center">
                        <s:button name="" value="Tutup" class="btn" onclick="tutup('${line.dokumenKewangan.idDokumenKewangan}');"/>
                    </p>--%>
                </div>
            </fieldset>
        </div>

    <%--</c:if>--%>
</s:form>
