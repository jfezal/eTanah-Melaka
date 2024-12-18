<%--
    Document   : carian_hakmilik_sebelum
    Created on : 03 November 2009, 4:40:06 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Carian Hakmilik Sebelum</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
         
                $.post(url,q,
                function(data){
                    if(data == '1')
                    {
                        alert('Sila masukan data pada label yang bertanda *. ');
                    }else{
                        $('#perincianHakmilik',opener.document).html(data);
                        self.close();}

                },'html');

            }
            $(document).ready(function() {
                maximizeWindow();

            });
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <fieldset class="aras1">
                    <legend>
                        Carian Hakmilik Sebelum
                    </legend>

                    <p>

                        <label for="ID Hakmilik">Id Hakmilik :</label>
                       <%-- <s:text name="idSebelum" id="idSebelum"/>--%>
                          <s:select name="idSebelum" id="idSebelum">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.listMohonHakmilikSebelum}" value="hakmilik.idHakmilik" label="hakmilik.idHakmilik"/>
                            </s:select>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="searchHakmilikSebelumByIDHakmilikSebelum" value="Cari" class="btn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </fieldset>
            </div>
            <br>
            <%--<div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Hakmilik Sebelum
                    </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listHakmilik}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="/pendaftaran/kemasukan_perincian_hakmilik?searchHakmilikAsalByIDHakmilikAsal" id="line">
                    <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}"/></display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik Sebelum" format="{0,date,dd-MM-yyyy}" class="idsebelum${line_rowNum}"/>
                     <display:column property="hakmilikSebelum.tarikhDaftar" title="Tarikh mula dimiliki" style="display:none;" class="tarikhmilik${line_rowNum}"/>


                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.listHakmilik) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="add" id="add" value="Tambah" class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </form:form>
</div>--%>
            <div class="subtitle">
                <%--<c:if test="${fn:length(actionBean.listHakmilik) > 0}">--%>
                <c:if test="${actionBean.idSebelum != null and actionBean.sejarahHakmilik.idHakmilik != null}">
                    <s:hidden name="sejarahHakmilik.idHakmilik"/>
                    <fieldset class="aras1">
                        <legend>Hakmilik Sebelum</legend>
                        <p><label>ID Hakmilik : </label>
                            ${actionBean.sejarahHakmilik.idHakmilik}
                        </p>
                        <%-- <p><label>Tarikh Mula dimiliki : </label>
                             <fmt:formatDate type="date"
                                             pattern="dd/MM/yyyy"
                                             value="${actionBean.sejarahHakmilik.tarikhDaftar}"/>
                         </p>--%>
                        <p><label>&nbsp;</label>
                            <s:button name="simpanHakmilikSblm" id="simpan" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                        </p>
                    </fieldset>
                </c:if>

                <%--  <c:if test="${fn:length(actionBean.listHakmilik) == 0 and actionBean.idSebelum != null}">
                      <fieldset class="aras1">
                          <legend>Hakmilik Sebelum</legend>
                          <p><label>ID Hakmilik : </label>
                              <s:text name="idSebelum" />
                          </p>
                            <p><label>Tarikh Mula dimiliki : </label>
                                <s:text name="sejarahHakmilik.tarikhDaftar" formatPattern="dd/MM/yy" class="datepicker" />
                            </p>

                        <p><label>&nbsp;</label>
                            <s:button name="simpanHakmilikSblm" id="simpan" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                        </p>
                    </fieldset>
                </c:if>--%>

            </div>
        </s:form>
    </body>
</html>