<%--
    Document   : popup_kodsyaratnyata
    Created on : Dec 28, 2009, 7:34:09 PM
    Author     : mohd.fairul
--%>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kemasukan Terperinci Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

        <script type="text/javascript">

            function popupCarian(pathCall)
            {
                var url ="${pageContext.request.contextPath}/daftar/nota/nota_daftar?"+pathCall;
                window.open(url,"eTanah1","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            }
            function add(event,f){
                var id =  ${actionBean.id};//$('input:text[name=id]').val();
                var cukaiBaru = $('input:text[name=cukaiBaru]').val();
                var kodSyaratNyata = $('input:text[name=kodSyaratNyata]').val();
                var kodSekatan = $('input:text[name=kodSekatan]').val();
                var kodOUM = $('#kodLuas').val();
                var katTanah = $('#katTanah').val();
                var luas = $('input:text[name=luas]').val();
                var q = $(f).formSerialize();
                var url = f.action + '?' + event +'&id='+id
                    +'&cukaiBaru='+cukaiBaru+'&luas='+luas+'&kodSyaratNyata='+kodSyaratNyata+
                    '&kodSekatan='+kodSekatan+'&kodLuas='+kodOUM+'&katTanah='+katTanah;

                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);

                },'html');

                $.prompt('Maklumat Berjaya Disimpan.',{
                    buttons:{Ok:true},
                    prefix:'jqismooth',
                    submit:function(v,m,f){
                        self.close();
                    }
                });


            }


        </script>
    </head>
    <body>



        <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">

            <s:messages/>
            <s:errors/>
            <div class="subtitle">

                <fieldset class="aras1">
                    <legend>Maklumat Hakmilik</legend>
                    <p>
                        <label>ID Permohonan :</label> ${actionBean.permohonan.idPermohonan}
                    </p>
                    <p>
                        <label>ID Hakmilik :</label> ${actionBean.idH}
                        <%--<s:text name="idHakmilik" id="idHakmilik" disabled="true"/>--%>
                    </p>
                   <%-- <p>
                        <label>ID Urusan :</label> <s:text name="id" id="id" disabled="true"/>
                        <s:hidden name="id" id="id"/>
                    </p>--%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKM' or actionBean.permohonan.kodUrusan.kod eq 'SSKPM'}">
                        <p>
                            <label>Kategori Tanah:</label>
                            <s:select name="katTanah" id="katTanah">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSKSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSTSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'TSM'}">

                          <p>
                              <label>Syarat Nyata :</label>

                              <s:text name="kodSyaratNyata" id="kodSyaratNyata" value=""/>&nbsp;<s:button name="cariKodSyaratNyata" value="Semak /Cari" id="cariKodSyaratNyata" class="btn" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=')" />
                          </p>
                          <p>
                              <label>Sekatan Kepentingan :</label>
                              <s:text name="kodSekatan" id="kodSekatan"/>&nbsp;<s:button name="cariKodSekatan" value="Semak /Cari" id="cariKodSekatan" class="btn" onclick="popupCarian('cariKodSekatan&kodSekatan=')"/>
                          </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SBSTM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                                  or actionBean.permohonan.kodUrusan.kod eq 'HLLA'
                                  or actionBean.permohonan.kodUrusan.kod eq 'HLLS'
                                  or actionBean.permohonan.kodUrusan.kod eq 'HLTE'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSTSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-D'
                          }">
                        <p >
                            <label>Keluasan :</label>
                            <s:text name="luas"></s:text>
                        </p>
                        <p>
                            <label>Unit Luas :</label>
                            <s:select name="kodLuas" id="kodLuas">
                                <s:option value="" >Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOM}" label="nama" value="kod" />
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSKSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSTSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'TSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PSM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SBSTM'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                                  or actionBean.permohonan.kodUrusan.kod eq 'HLLS'
                          }">
                        <p>
                            <label>Cukai Tanah (RM):</label>
                            <s:text name="cukaiBaru" />
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'
                                    or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
                        <p>
                            <label>Cukai Tanah (RM):</label>
                            <s:text name="cukaiBaru" />
                        </p>
                        </c:if>
                    <table style="margin-left: auto; margin-right: auto;">
                        <tr>
                            <td>&nbsp;</td>
                            <td><div >

                                    <s:button name="simpanSingle" value="Simpan" class="btn" onclick="add(this.name, this.form)"/>


                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </s:form>


    </body>
</html>