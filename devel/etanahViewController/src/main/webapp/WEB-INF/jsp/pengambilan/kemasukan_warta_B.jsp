<%-- 
    Document   : kemasukan_warta_B
    Created on : 14-Jun-2010, 10:18:24
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
<script type="text/javascript">

function validation() {
        if($("#nowarta").val() == ""){
            alert('Sila masukkan " No Lembaran Piawai " terlebih dahulu.');
            $("#nolitho").focus();
            return true;
        }
        if($("#tarikh").val() == ""){
            alert('Sila pilih " Daerah " terlebih dahulu.');
            $("#daerah").focus();
            return true;
        }
    }


          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });



  </script>

<s:form beanclass="etanah.view.stripes.pengambilan.wartaActionBean">
<s:messages/>
<s:errors/>

<c:if test="${edit}">
             <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Penyediaan Borang B
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left" width="30%">No. Warta :</td>
                                <td align="left" class="input1" ><s:text name="permohonanPengambilan.noWarta" id="nowarta"/> </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tarikh Warta :</td>
                                <td align="left" class="input1" ><s:text name="tarikhWarta" id="datepicker" class="datepicker"/></td>
                            </tr>
                        </table>
                            <br>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>
                            <s:button name="simpanwartasek4b" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
                            </p>
                    </div>
                </fieldset>
            </div>
                    </c:if>
<c:if test="${!edit}">
             <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      Borang B
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left" width="30%">No. Warta :</td>
                                <td align="left" class="input1" >${actionBean.permohonanPengambilan.noWarta}&nbsp;<%-- <s:text name="permohonanPengambilan.noWarta" id="nowarta"/>--%> </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tarikh Warta :</td>
                                <td align="left" class="input1" >${actionBean.permohonanPengambilan.tarikhWarta}<%--<s:text name="tarikhWarta" id="datepicker" class="datepicker"/>--%></td>
                            </tr>
                        </table>
                            <br>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>
                            <%--<s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />--%>
                            </p>
                    </div>
                </fieldset>
            </div>
                            
</c:if>
   </s:form>
